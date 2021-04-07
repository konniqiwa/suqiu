package com.suqiu.goods.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.suqiu.goods.dao.SmsPeopleRecommendMapper;
import com.suqiu.goods.pojo.SmsPeopleRecommend;
import com.suqiu.goods.service.SmsPeopleRecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/****
 * @Author:admin
 * @Description:SmsPeopleRecommend业务层接口实现类
 * @Date 2019/6/14 0:16
 *****/
@Service
public class SmsPeopleRecommendServiceImpl implements SmsPeopleRecommendService {

    @Autowired
    private SmsPeopleRecommendMapper smsPeopleRecommendMapper;


    /**
     * SmsPeopleRecommend条件+分页查询
     * @param smsPeopleRecommend 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<SmsPeopleRecommend> findPage(SmsPeopleRecommend smsPeopleRecommend, int page, int size){
        //分页
        PageHelper.startPage(page,size);
        //搜索条件构建
        Example example = createExample(smsPeopleRecommend);
        //执行搜索
        return new PageInfo<SmsPeopleRecommend>(smsPeopleRecommendMapper.selectByExample(example));
    }

    /**
     * SmsPeopleRecommend分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<SmsPeopleRecommend> findPage(int page, int size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return new PageInfo<SmsPeopleRecommend>(smsPeopleRecommendMapper.selectAll());
    }

    /**
     * SmsPeopleRecommend条件查询
     * @param smsPeopleRecommend
     * @return
     */
    @Override
    public List<SmsPeopleRecommend> findList(SmsPeopleRecommend smsPeopleRecommend){
        //构建查询条件
        Example example = createExample(smsPeopleRecommend);
        //根据构建的条件查询数据
        return smsPeopleRecommendMapper.selectByExample(example);
    }


    /**
     * SmsPeopleRecommend构建查询对象
     * @param smsPeopleRecommend
     * @return
     */
    public Example createExample(SmsPeopleRecommend smsPeopleRecommend){
        Example example=new Example(SmsPeopleRecommend.class);
        Example.Criteria criteria = example.createCriteria();
        if(smsPeopleRecommend!=null){
            // 商品id
            if(!StringUtils.isEmpty(smsPeopleRecommend.getProductId())){
                    criteria.andEqualTo("productId",smsPeopleRecommend.getProductId());
            }
            // 商品名称
            if(!StringUtils.isEmpty(smsPeopleRecommend.getProductName())){
                    criteria.andEqualTo("productName",smsPeopleRecommend.getProductName());
            }
            // 是否人气推荐
            if(!StringUtils.isEmpty(smsPeopleRecommend.getPeopleRecommendStatus())){
                    criteria.andEqualTo("peopleRecommendStatus",smsPeopleRecommend.getPeopleRecommendStatus());
            }
            // 排序
            if(!StringUtils.isEmpty(smsPeopleRecommend.getPeopleSort())){
                    criteria.andEqualTo("peopleSort",smsPeopleRecommend.getPeopleSort());
            }
            // 是否新品推荐
            if(!StringUtils.isEmpty(smsPeopleRecommend.getNewRecommendStatus())){
                    criteria.andEqualTo("newRecommendStatus",smsPeopleRecommend.getNewRecommendStatus());
            }
            // 排序
            if(!StringUtils.isEmpty(smsPeopleRecommend.getNewSort())){
                    criteria.andEqualTo("newSort",smsPeopleRecommend.getNewSort());
            }
        }
        return example;
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void delete(Long id){
        smsPeopleRecommendMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改SmsPeopleRecommend
     * @param smsPeopleRecommend
     */
    @Override
    public void update(SmsPeopleRecommend smsPeopleRecommend){
        smsPeopleRecommendMapper.updateByPrimaryKey(smsPeopleRecommend);
    }

    /**
     * 增加SmsPeopleRecommend
     * @param smsPeopleRecommend
     */
    @Override
    public void add(SmsPeopleRecommend smsPeopleRecommend){
        smsPeopleRecommendMapper.insert(smsPeopleRecommend);
    }

    /**
     * 根据ID查询SmsPeopleRecommend
     * @param id
     * @return
     */
    @Override
    public SmsPeopleRecommend findById(Long id){
        return  smsPeopleRecommendMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询SmsPeopleRecommend全部数据
     * @return
     */
    @Override
    public List<SmsPeopleRecommend> findAll() {
        return smsPeopleRecommendMapper.selectAll();
    }
}
