package com.suqiu.goods.service;

import com.github.pagehelper.PageInfo;
import com.suqiu.goods.pojo.SmsPeopleRecommend;

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
     * @param id
     */
    void delete(Long id);

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
     * @param id
     * @return
     */
     SmsPeopleRecommend findById(Long id);

    /***
     * 查询所有SmsPeopleRecommend
     * @return
     */
    List<SmsPeopleRecommend> findAll();
}
