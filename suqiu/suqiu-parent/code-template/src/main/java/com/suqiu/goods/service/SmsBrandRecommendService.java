package com.suqiu.goods.service;
import com.suqiu.goods.pojo.SmsBrandRecommend;
import com.github.pagehelper.PageInfo;
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
     * @param id
     */
    void delete(Long id);

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
     * @param id
     * @return
     */
     SmsBrandRecommend findById(Long id);

    /***
     * 查询所有SmsBrandRecommend
     * @return
     */
    List<SmsBrandRecommend> findAll();
}
