package com.suqiu.goods.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.suqiu.goods.dao.SmsBrandRecommendMapper;
import com.suqiu.goods.pojo.SmsBrandRecommend;
import com.suqiu.goods.service.SmsBrandRecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/****
 * @Author:admin
 * @Description:SmsBrandRecommend业务层接口实现类
 * @Date 2019/6/14 0:16
 *****/
@Service
public class SmsBrandRecommendServiceImpl implements SmsBrandRecommendService {

    @Autowired
    private SmsBrandRecommendMapper smsBrandRecommendMapper;

    /**
     * SmsBrandRecommend条件+分页查询
     * @param smsBrandRecommend 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<SmsBrandRecommend> findPage(SmsBrandRecommend smsBrandRecommend, int page, int size){
        //分页
        PageHelper.startPage(page,size);
        //搜索条件构建
        Example example = createExample(smsBrandRecommend);
        //执行搜索
        return new PageInfo<SmsBrandRecommend>(smsBrandRecommendMapper.selectByExample(example));
    }

    /**
     * SmsBrandRecommend分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<SmsBrandRecommend> findPage(int page, int size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return new PageInfo<SmsBrandRecommend>(smsBrandRecommendMapper.selectAll());
    }

    /**
     * SmsBrandRecommend条件查询
     * @param smsBrandRecommend
     * @return
     */
    @Override
    public List<SmsBrandRecommend> findList(SmsBrandRecommend smsBrandRecommend){
        //构建查询条件
        Example example = createExample(smsBrandRecommend);
        //根据构建的条件查询数据
        return smsBrandRecommendMapper.selectByExample(example);
    }


    /**
     * SmsBrandRecommend构建查询对象
     * @param smsBrandRecommend
     * @return
     */
    public Example createExample(SmsBrandRecommend smsBrandRecommend){
        Example example=new Example(SmsBrandRecommend.class);
        Example.Criteria criteria = example.createCriteria();
        if(smsBrandRecommend!=null){
            // id
            if(!StringUtils.isEmpty(smsBrandRecommend.getBrandId())){
                    criteria.andEqualTo("brandId",smsBrandRecommend.getBrandId());
            }
            // 品牌名称
            if(!StringUtils.isEmpty(smsBrandRecommend.getBrandName())){
                    criteria.andEqualTo("brandName",smsBrandRecommend.getBrandName());
            }
            // 是否推荐
            if(!StringUtils.isEmpty(smsBrandRecommend.getRecommendStatus())){
                    criteria.andEqualTo("recommendStatus",smsBrandRecommend.getRecommendStatus());
            }
            // 排序
            if(!StringUtils.isEmpty(smsBrandRecommend.getSort())){
                    criteria.andEqualTo("sort",smsBrandRecommend.getSort());
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
        smsBrandRecommendMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改SmsBrandRecommend
     * @param smsBrandRecommend
     */
    @Override
    public void update(SmsBrandRecommend smsBrandRecommend){
        smsBrandRecommendMapper.updateByPrimaryKey(smsBrandRecommend);
    }

    /**
     * 增加SmsBrandRecommend
     * @param smsBrandRecommend
     */
    @Override
    public void add(SmsBrandRecommend smsBrandRecommend){
        smsBrandRecommendMapper.insert(smsBrandRecommend);
    }

    /**
     * 根据ID查询SmsBrandRecommend
     * @param id
     * @return
     */
    @Override
    public SmsBrandRecommend findById(Long id){
        return  smsBrandRecommendMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询SmsBrandRecommend全部数据
     * @return
     */
    @Override
    public List<SmsBrandRecommend> findAll() {
        return smsBrandRecommendMapper.selectAll();
    }
}
