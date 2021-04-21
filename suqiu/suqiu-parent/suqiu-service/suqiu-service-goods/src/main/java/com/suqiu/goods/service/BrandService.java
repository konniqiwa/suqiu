package com.suqiu.goods.service;

import com.suqiu.goods.pojo.Brand;
import com.github.pagehelper.PageInfo;
import com.suqiu.model.req.BrandListModel;
import com.suqiu.model.req.CreateOrUpdateBrandModel;
import com.suqiu.model.req.IsFactoryStatusModel;
import com.suqiu.model.req.IsShowStatusModel;

import java.util.List;

/****
 * @Author:admin
 * @Description:Brand业务层接口
 * @Date 2019/6/14 0:16
 *****/
public interface BrandService {

    /***
     * Brand多条件分页查询
     * @param brand
     * @param page
     * @param size
     * @return
     */
    PageInfo<Brand> findPage(Brand brand, int page, int size);

    /***
     * Brand分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<Brand> findPage(int page, int size);

    /***
     * Brand多条件搜索方法
     * @param brand
     * @return
     */
    List<Brand> findList(Brand brand);

    /***
     * 删除Brand
     * @param id
     */
    void delete(Integer id);

    /***
     * 修改Brand数据
     * @param brand
     */
    void update(Brand brand);

    /***
     * 新增Brand
     * @param brand
     */
    void add(Brand brand);

    /**
     * 根据ID查询Brand
     *
     * @param id
     * @return
     */
    Brand findById(Integer id);

    /***
     * 查询所有Brand
     * @return
     */
    List<Brand> findAll();


    List<Brand> findByCategory(Integer id);

    /**
     * 根据条件查询所有brand
     *
     * @return
     */
    List<Brand> findAllBrand(BrandListModel reqModel);

    /**
     * 删除某品牌
     *
     * @param id
     */
    void deleteBrand(Long id);

    /**
     * 增加或编辑品牌
     *
     * @param model
     */
    void createOrUpdate(CreateOrUpdateBrandModel model);

    /**
     * 修改品牌制造商状态
     *
     * @param id
     * @param model
     */
    void isFactoryStatus(Long id, IsFactoryStatusModel model);

    /**
     * 是否显示品牌
     *
     * @param model
     */
    void isShowStatus(IsShowStatusModel model);

    /**
     * 获取品牌详情
     *
     * @param id
     * @return
     */
    Brand brandInfo(Long id);
}
