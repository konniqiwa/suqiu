package com.suqiu.goods.service;

import com.github.pagehelper.PageInfo;
import com.suqiu.goods.pojo.SmsPeopleRecommend;
import com.suqiu.model.req.CreatePeopleRecommendModel;
import com.suqiu.model.req.PeopleRecommendModel;
import com.suqiu.model.res.SmsPeopleRecommendListDTO;

import java.util.List;

/****
 * @Author:admin
 * @Description:SmsPeopleRecommend业务层接口
 * @Date 2019/6/14 0:16
 *****/
public interface SmsPeopleRecommendService {

    /***
     * SmsPeopleRecommend多条件分页查询
     * @param smsPeopleRecommend
     * @param page
     * @param size
     * @return
     */
    PageInfo<SmsPeopleRecommend> findPage(SmsPeopleRecommend smsPeopleRecommend, int page, int size);

    /***
     * SmsPeopleRecommend分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<SmsPeopleRecommend> findPage(int page, int size);

    /***
     * SmsPeopleRecommend多条件搜索方法
     * @param smsPeopleRecommend
     * @return
     */
    List<SmsPeopleRecommend> findList(SmsPeopleRecommend smsPeopleRecommend);

    /***
     * 删除SmsPeopleRecommend
     * @param ids
     */
    void delete(List<Long> ids);

    /***
     * 修改SmsPeopleRecommend数据
     * @param smsPeopleRecommend
     */
    void update(SmsPeopleRecommend smsPeopleRecommend);

    /***
     * 新增SmsPeopleRecommend
     * @param smsPeopleRecommend
     */
    void add(SmsPeopleRecommend smsPeopleRecommend);

    /**
     * 根据ID查询SmsPeopleRecommend
     *
     * @param id
     * @return
     */
    SmsPeopleRecommend findById(Long id);

    /***
     * 查询所有SmsPeopleRecommend
     * @return
     */
    List<SmsPeopleRecommend> findAll();


    /**
     * 根据条件查询推荐列表
     *
     * @param model
     * @param type
     * @return
     */
    SmsPeopleRecommendListDTO smsPeopleRecommendList(PeopleRecommendModel model, int type);


    /**
     * 设置推荐状态
     *
     * @param ids
     * @param recommendStatus
     * @param type
     */
    void isPeopleRecommend(List<Long> ids, int recommendStatus, int type);

    /**
     * 设置排序
     *
     * @param id
     * @param sort
     */
    void peopleSort(Long id, int sort, int type);


    /**
     * 增加推荐
     *
     * @param peopleRecommend
     * @param type
     */
    void create(List<CreatePeopleRecommendModel> peopleRecommend, int type);
}
