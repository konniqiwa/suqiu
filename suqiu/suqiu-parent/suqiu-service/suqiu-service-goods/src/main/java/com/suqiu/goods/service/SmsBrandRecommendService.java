package com.suqiu.goods.service;

import com.github.pagehelper.PageInfo;
import com.suqiu.goods.pojo.SmsBrandRecommend;
import com.suqiu.model.req.*;
import com.suqiu.model.res.SmsNewRecommendListDTO;
import com.suqiu.model.res.SmsPeopleRecommendListDTO;

import java.util.List;

/****
 * @Author:admin
 * @Description:SmsBrandRecommend业务层接口
 * @Date 2019/6/14 0:16
 *****/
public interface SmsBrandRecommendService {

    /***
     * SmsBrandRecommend多条件分页查询
     * @param smsBrandRecommend
     * @param page
     * @param size
     * @return
     */
    PageInfo<SmsBrandRecommend> findPage(SmsBrandRecommend smsBrandRecommend, int page, int size);

    /***
     * SmsBrandRecommend分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<SmsBrandRecommend> findPage(int page, int size);

    /***
     * SmsBrandRecommend多条件搜索方法
     * @param smsBrandRecommend
     * @return
     */
    List<SmsBrandRecommend> findList(SmsBrandRecommend smsBrandRecommend);

    /***
     * 删除SmsBrandRecommend
     * @param model
     */
    void delete(DeleteNewBrandModel model);

    /***
     * 修改SmsBrandRecommend数据
     * @param smsBrandRecommend
     */
    void update(SmsBrandRecommend smsBrandRecommend);

    /***
     * 新增SmsBrandRecommend
     * @param smsBrandRecommend
     */
    void add(SmsBrandRecommend smsBrandRecommend);

    /**
     * 根据ID查询SmsBrandRecommend
     *
     * @param id
     * @return
     */
    SmsBrandRecommend findById(Long id);

    /***
     * 查询所有SmsBrandRecommend
     * @return
     */
    List<SmsBrandRecommend> findAll();

    /**
     * 根据条件查询推荐列表
     *
     * @param model
     * @return
     */
    SmsNewRecommendListDTO smsPeopleRecommendList(NewRecommendModel model);


    /**
     * 设置推荐状态
     *
     * @param model
     */
    void isPeopleRecommend(IsNewRecommendModel model);

    /**
     * 设置排序
     *
     * @param id
     * @param model
     */
    void peopleSort(Long id, NewSortBrandModel model);


    /**
     * 增加推荐
     *
     * @param model
     */
    void create(CreateNewBrandModel model);
}
