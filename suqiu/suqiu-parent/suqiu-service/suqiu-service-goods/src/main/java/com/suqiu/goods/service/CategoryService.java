package com.suqiu.goods.service;

import com.suqiu.goods.pojo.Category;
import com.github.pagehelper.PageInfo;
import com.suqiu.model.req.BasePageModel;
import com.suqiu.model.req.IsNevCategoryModel;
import com.suqiu.model.req.IsShowCategoryModel;
import com.suqiu.model.req.UpdateOrAddModel;

import java.util.List;

/****
 * @Author:admin
 * @Description:Category业务层接口
 * @Date 2019/6/14 0:16
 *****/
public interface CategoryService {


    /***
     * Category多条件分页查询
     * @param category
     * @param page
     * @param size
     * @return
     */
    PageInfo<Category> findPage(Category category, int page, int size);

    /***
     * Category分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<Category> findPage(int page, int size);

    /***
     * Category多条件搜索方法
     * @param category
     * @return
     */
    List<Category> findList(Category category);

    /***
     * 删除Category
     * @param id
     */
    void delete(Integer id);

    /***
     * 修改Category数据
     * @param category
     */
    void update(Category category);

    /***
     * 新增Category
     * @param category
     */
    void add(Category category);

    /**
     * 根据ID查询Category
     *
     * @param id
     * @return
     */
    Category findById(Integer id);

    /***
     * 查询所有Category
     * @return
     */
    List<Category> findAll();


    List<Category> findByParentId(Integer pid, BasePageModel reqModel);

    /**
     * 查询所有分类,树形结构
     *
     * @return
     */
    List<Category> findAllTree();

    void setLevel();

    /**
     * 是否导航
     *
     * @param id
     * @param model
     */
    void isNev(Integer id, IsNevCategoryModel model);

    /**
     * 是否显示
     *
     * @param id
     * @param model
     */
    void isShow(Integer id, IsShowCategoryModel model);

    /**
     * 删除分类
     *
     * @param id
     */
    void deleteCategory(Integer id);

    /**
     * 编辑或增加分类
     *
     * @param updateOrAddModel
     */
    void updateOrAdd(UpdateOrAddModel updateOrAddModel);

    /**
     * 查询两级分类
     *
     * @return
     */
    List<Category> withAttr();

    /**
     * 查询分类详情
     *
     * @param id
     * @return
     */
    Category info(Long id);
}
