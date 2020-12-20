package com.suqiu.search.service.impl;

import com.alibaba.fastjson.JSON;
import com.suqiu.goods.pojo.Sku;
import com.suqiu.search.dao.SkuEsMapper;
import com.suqiu.goods.feign.SkuFeign;
import com.suqiu.search.pojo.SkuInfo;
import com.suqiu.search.service.SkuService;
import entity.Result;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class SkuServiceImpl implements SkuService {

    @Autowired
    private SkuFeign skuFeign;

    @Autowired
    private SkuEsMapper skuEsMapper;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    /**
     * 导入到es
     */
    @Override
    public void importData() {
        //调用feign查询所有sku
        Result<List<Sku>> skuFeignAll = skuFeign.findAll();


        //转换成json的 List<SkuInfo>
        List<SkuInfo> skuInfos = JSON.parseArray(JSON.toJSONString(skuFeignAll.getData()), SkuInfo.class);

        for (SkuInfo skuInfo : skuInfos) {
            Map<String,Object>  map = JSON.parseObject(skuInfo.getSpec(), Map.class);
            skuInfo.setSpecMap(map);
        }

        //将数据导入es
        skuEsMapper.saveAll(skuInfos);

    }

    /**
     * 搜索功能
     * @param searchMap
     * @return
     */
    @Override
    public Map<String, Object> search(Map<String, String> searchMap) {

        //搜索条件构建
        NativeSearchQueryBuilder builder = buildBasicQuery(searchMap);

        //集合搜索
        Map<String, Object> resultMap = searchList(builder);

        //当用户点击了某一个分类，则不需要查询分类
        /*if (searchMap == null || StringUtils.isEmpty(searchMap.get("category"))) {
            //分类分组搜索
            List<String> categoryList = getStringsCategoryList(builder);
            resultMap.put("categoryList",categoryList);
        }


        if (searchMap == null || StringUtils.isEmpty(searchMap.get("brand"))){
            //品牌分组搜索
            List<String> brandList = getStringsBrandList(builder);
            resultMap.put("brandList",brandList);
        }


        //规格分组合并搜索
        Map<String, Set<String>> specList = getStringsSpecList(builder);
        resultMap.put("specList",specList);*/

        Map<String, Object> groupList = getStringsGroupList(builder, searchMap);
        resultMap.putAll(groupList);
        return resultMap;
    }

    /**
     * 搜索条件构建
     * @param searchMap
     * @return
     */
    public NativeSearchQueryBuilder buildBasicQuery(Map<String, String> searchMap) {
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();

        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();

        if (searchMap != null && searchMap.size() > 0) {

            //输入了关键词
            String keywords = searchMap.get("keywords");
            if (!StringUtils.isEmpty(keywords)) {
                queryBuilder.must(QueryBuilders.queryStringQuery(keywords).field("name"));
            }

            //输入了category
            if (!StringUtils.isEmpty(searchMap.get("category"))) {
                queryBuilder.must(QueryBuilders.termQuery("categoryName",searchMap.get("category")));
            }

            //输入了brand
            if (!StringUtils.isEmpty(searchMap.get("brand"))) {
                queryBuilder.must(QueryBuilders.termQuery("brandName",searchMap.get("brand")));
            }

            //输入规格
            for (Map.Entry<String, String> entry : searchMap.entrySet()) {
                String key = entry.getKey();
                if (key.startsWith("spec_")) {
                    String value = entry.getValue();
                    queryBuilder.must(QueryBuilders.termQuery("specMap." + key.substring(5) + ".keyword",value));
                }
            }

            //输入价格区间
            String price = searchMap.get("price");
            if (!StringUtils.isEmpty(price)) {

                price = price.replace("元","").replace("以上","");
                String[] prices = price.split("-");

                if (prices != null && prices.length > 0) {
                    queryBuilder.must(QueryBuilders.rangeQuery("price").gt(prices[0]));
                    if (prices.length == 2) {
                        queryBuilder.must(QueryBuilders.rangeQuery("price").lte(prices[1]));
                    }
                }
            }
        }

        //排序实现
        String sortField = searchMap.get("sortField");  //指定域
        String sortRule = searchMap.get("sortRule");    //指定排序规则
        if (!StringUtils.isEmpty(sortField) && !StringUtils.isEmpty(sortRule)) {
            builder.withSort(new FieldSortBuilder(sortField).order(SortOrder.valueOf(sortRule)));
        }



        //分页，如果不传分页参数，默认为第一页
        Integer pageNum = coverterPage(searchMap);
        Integer size = 20; //每一页显示条数
        builder.withPageable(PageRequest.of(pageNum - 1, size));

        builder.withQuery(queryBuilder);
        return builder;
    }

    /**
     * 接受前端传来的分页参数
     * @param searchMap
     * @return
     */
    public Integer coverterPage(Map<String,String> searchMap) {
        if (searchMap != null && searchMap.size() > 0) {
            String pageNum = searchMap.get("pageNum");
            try {
                return Integer.parseInt(pageNum);
            } catch (NumberFormatException e) {
               // e.printStackTrace();
            }

        }
        return 1;
    }

    /**
     * 所有结果搜索
     * @param builder
     * @return
     */
    public Map<String, Object> searchList(NativeSearchQueryBuilder builder) {

        //高亮配置
        HighlightBuilder.Field field = new HighlightBuilder.Field("name");
        //前缀：<em style="color:red">
        //后缀: </em>

        field.preTags("<em style=\"color:red\">");
        field.postTags("</em>");
        field.fragmentSize(100);    //碎片长度


        //添加高亮
        builder.withHighlightFields(field);

        //AggregatedPage<SkuInfo> page = elasticsearchTemplate.queryForPage(builder.build(), SkuInfo.class);
        AggregatedPage<SkuInfo> page = elasticsearchTemplate.queryForPage(builder.build(), SkuInfo.class, new SearchResultMapper() {
            @Override
            public <T> AggregatedPage<T> mapResults(SearchResponse searchResponse, Class<T> aClass, Pageable pageable) {

                List<T> list = new ArrayList<T>();

                //获取所有数据
                for (SearchHit hit : searchResponse.getHits()) {
                    //获取非高亮数据
                    SkuInfo skuInfo = JSON.parseObject(hit.getSourceAsString(), SkuInfo.class);

                    //获取某个域高亮数据
                    HighlightField highlightField = hit.getHighlightFields().get("name");
                    if (highlightField != null && highlightField.getFragments() != null) {
                        //获取高亮数据
                        Text[] fragments = highlightField.getFragments();

                        StringBuffer buffer = new StringBuffer();
                        //读取高亮数据并写进buffer
                        for (Text fragment : fragments) {
                            buffer.append(fragment.toString());
                        }
                       // System.out.println(buffer.toString());
                        //将非高亮替换成高亮数据
                        skuInfo.setName(buffer.toString());

                    }
                    //讲替换后的数据添加到list，后面返回值需要
                    list.add((T) skuInfo);

                }

                return new AggregatedPageImpl<T>(list,pageable,searchResponse.getHits().getTotalHits());
            }
        });

        List<SkuInfo> skuInfos = page.getContent();

        long totalElements = page.getTotalElements();

        int totalPages = page.getTotalPages();

        Map<String, Object> resultMap = new HashMap<String, Object>();

        resultMap.put("rows",skuInfos);
        resultMap.put("total",totalElements);
        resultMap.put("totalPages",totalPages);

        /**
         * 获取搜索数据
         */
        NativeSearchQuery build = builder.build();
        Pageable pageable = build.getPageable();
        int pageNumber = pageable.getPageNumber();  //当前页
        int pageSize = pageable.getPageSize();  //每页的条数

        resultMap.put("pageNumber",pageNumber);
        resultMap.put("pageSize",pageSize);



        return resultMap;
    }

    /**
     * 分组搜索（所有分组）
     * @param builder
     * @return
     */
    public Map<String,Object> getStringsGroupList(NativeSearchQueryBuilder builder,Map<String,String> searchMap) {

        if (searchMap == null || StringUtils.isEmpty(searchMap.get("category"))) {
            builder.addAggregation(AggregationBuilders.terms("skuCategory").field("categoryName").size(10000));
        }

        if (searchMap == null || StringUtils.isEmpty(searchMap.get("brand"))) {
            builder.addAggregation(AggregationBuilders.terms("skuBrand").field("brandName").size(10000));
        }

        builder.addAggregation(AggregationBuilders.terms("skuSpec").field("spec.keyword").size(10000));

        AggregatedPage<SkuInfo> aggregatedPage = elasticsearchTemplate.queryForPage(builder.build(), SkuInfo.class);

        Map<String,Object> searchGroupResult = new HashMap<String,Object>();

        if (searchMap == null || StringUtils.isEmpty(searchMap.get("category"))) {
            StringTerms categoryTerms = aggregatedPage.getAggregations().get("skuCategory");
            List<String> categoryList = getGroupList(categoryTerms);
            searchGroupResult.put("categoryList",categoryList);
        }

        if (searchMap == null || StringUtils.isEmpty(searchMap.get("brand"))) {
            StringTerms brandTerms = aggregatedPage.getAggregations().get("skuBrand");
            List<String> brandList = getGroupList(brandTerms);
            searchGroupResult.put("brandList",brandList);
        }

        StringTerms specTerms = aggregatedPage.getAggregations().get("skuSpec");
        Map<String, Set<String>> allSpec = putAllSpec(specTerms);
        searchGroupResult.put("specList",allSpec);

        return searchGroupResult;
    }

    /**
     * 获取分组数据
     */
    public List<String> getGroupList(StringTerms stringTerms) {
        List<String> groupList = new ArrayList<String>();
        for (StringTerms.Bucket bucket : stringTerms.getBuckets()) {
            groupList.add(bucket.getKeyAsString());

        }
        return groupList;
    }

    /**
     * 分类分组搜索（获得分类）
     * @param builder
     * @return
     */
    /*public List<String> getStringsCategoryList(NativeSearchQueryBuilder builder) {
        builder.addAggregation(AggregationBuilders.terms("skuCategorygroup").field("categoryName").size(10000));

        AggregatedPage<SkuInfo> aggregatedPage = elasticsearchTemplate.queryForPage(builder.build(), SkuInfo.class);

        StringTerms stringTerms = aggregatedPage.getAggregations().get("skuCategorygroup");

        List<String> categoryList = new ArrayList<String>();
        for (StringTerms.Bucket bucket : stringTerms.getBuckets()) {
            categoryList.add(bucket.getKeyAsString());

        }
        return categoryList;
    }*/

    /**
     * 品牌分组搜索（获得品牌）
     * @param builder
     * @return
     */
    /*public List<String> getStringsBrandList(NativeSearchQueryBuilder builder) {
        builder.addAggregation(AggregationBuilders.terms("skuBrand").field("brandName").size(10000));

        AggregatedPage<SkuInfo> aggregatedPage = elasticsearchTemplate.queryForPage(builder.build(), SkuInfo.class);

        StringTerms stringTerms = aggregatedPage.getAggregations().get("skuBrand");

        List<String> brandList = new ArrayList<String>();
        for (StringTerms.Bucket bucket : stringTerms.getBuckets()) {
            brandList.add(bucket.getKeyAsString());

        }
        return brandList;
    }*/

    /**
     * 规格分组搜索
     * @param builder
     * @return
     */
    /*public Map<String, Set<String>> getStringsSpecList(NativeSearchQueryBuilder builder) {
        builder.addAggregation(AggregationBuilders.terms("skuSpec").field("spec.keyword").size(10000));

        AggregatedPage<SkuInfo> aggregatedPage = elasticsearchTemplate.queryForPage(builder.build(), SkuInfo.class);

        StringTerms stringTerms = aggregatedPage.getAggregations().get("skuSpec");

        //合并所有规格
        Map<String, Set<String>> specMap = putAllSpec(stringTerms);
        return specMap;
    }*/

    /**
     * 合并所有规格
     * @param stringTerms
     * @return
     */
    public Map<String, Set<String>> putAllSpec(StringTerms stringTerms) {
        Map<String, Set<String>> specMap = new HashMap<String, Set<String>>();
        Set<String> specList = new HashSet<>();
        if (stringTerms != null) {
            for (StringTerms.Bucket bucket : stringTerms.getBuckets()) {
                specList.add(bucket.getKeyAsString());
            }
        }
        for (String specjson : specList) {
            Map<String, String> map = JSON.parseObject(specjson, Map.class);
            for (Map.Entry<String, String> entry : map.entrySet()) {//
                String key = entry.getKey();        //规格名字
                String value = entry.getValue();    //规格选项值
                //获取当前规格名字对应的规格数据
                Set<String> specValues = specMap.get(key);
                if (specValues == null) {
                    specValues = new HashSet<String>();
                }
                //将当前规格加入到集合中
                specValues.add(value);
                //将数据存入到specMap中
                specMap.put(key, specValues);
            }
        }
        return specMap;
    }
}
