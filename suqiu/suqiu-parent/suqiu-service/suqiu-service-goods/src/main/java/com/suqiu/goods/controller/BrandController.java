package com.suqiu.goods.controller;

import com.suqiu.goods.pojo.Brand;
import com.suqiu.goods.service.BrandService;
import com.github.pagehelper.PageInfo;
import com.suqiu.model.req.BrandListModel;
import com.suqiu.model.req.CreateOrUpdateBrandModel;
import com.suqiu.model.req.IsFactoryStatusModel;
import com.suqiu.model.req.IsShowStatusModel;
import entity.JsonDTO;
import entity.Result;
import entity.StatusCode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/****
 * @Author:suqiu
 * @Description:
 * @Date 2020/6/14 0:18
 *****/

@RestController
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping("/getInfo/{id}")
    public JsonDTO brandInfo(@PathVariable Long id) {
        Brand brand = brandService.brandInfo(id);
        return JsonDTO.createInstance().setStatus(JsonDTO.SUCCESS).setMsg("获取品牌详情").setData(brand);
    }

    @PostMapping("/update/showStatus")
    public JsonDTO isShowStatus(@RequestParam List<Integer> ids, @RequestParam int showStatus) {
        brandService.isShowStatus(ids, showStatus);
        return JsonDTO.createInstance().setStatus(JsonDTO.SUCCESS).setMsg("是否显示");
    }

    @PostMapping("/isFactoryStatus")
    public JsonDTO isFactoryStatus(@RequestParam List<Long> ids, @RequestParam int factoryStatus) {
        brandService.isFactoryStatus(ids, factoryStatus);
        return JsonDTO.createInstance().setStatus(JsonDTO.SUCCESS).setMsg("修改品牌制造商状态");
    }

    @PostMapping("/createOrUpdate")
    public JsonDTO createOrUpdate(CreateOrUpdateBrandModel model) {
        brandService.createOrUpdate(model);
        return JsonDTO.createInstance().setStatus(JsonDTO.SUCCESS).setMsg("编辑或增加品牌");
    }

    @PostMapping("/delete/{id}")
    public JsonDTO deleteBrand(@PathVariable Long id) {
        brandService.deleteBrand(id);
        return JsonDTO.createInstance().setStatus(JsonDTO.SUCCESS).setMsg("删除品牌");
    }

    /***
     * 查询Brand全部数据
     * @return
     */
    @PostMapping("/list")
    public JsonDTO findAll(@RequestBody BrandListModel reqModel) {
        //调用BrandService实现查询所有Brand
        List<Brand> list = brandService.findAllBrand(reqModel);
        PageInfo pageInfo = new PageInfo(list);
        long total = pageInfo.getTotal();
        return JsonDTO.createInstance().setStatus(JsonDTO.SUCCESS).put("list", list).put("total", total);
    }

    /**
     * @return
     */
    @GetMapping("/category/{id}")
    public Result<List<Brand>> findBrandByCategory(@PathVariable(name = "id") Integer id) {
        List<Brand> brandList = brandService.findByCategory(id);

        return new Result<List<Brand>>(true, StatusCode.OK, "查询品牌列表成功", brandList);

    }

    /***
     * Brand分页条件搜索实现
     * @param brand
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}")
    public Result<PageInfo> findPage(@RequestBody(required = false) Brand brand, @PathVariable int page, @PathVariable int size) {
        //调用BrandService实现分页条件查询Brand
        PageInfo<Brand> pageInfo = brandService.findPage(brand, page, size);
        return new Result(true, StatusCode.OK, "查询成功", pageInfo);
    }

    /***
     * Brand分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}")
    public Result<PageInfo> findPage(@PathVariable int page, @PathVariable int size) {
        //调用BrandService实现分页查询Brand
        PageInfo<Brand> pageInfo = brandService.findPage(page, size);
        return new Result<PageInfo>(true, StatusCode.OK, "查询成功", pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param brand
     * @return
     */
    @PostMapping(value = "/search")
    public Result<List<Brand>> findList(@RequestBody(required = false) Brand brand) {
        //调用BrandService实现条件查询Brand
        List<Brand> list = brandService.findList(brand);
        return new Result<List<Brand>>(true, StatusCode.OK, "查询成功", list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable Integer id) {
        //调用BrandService实现根据主键删除
        brandService.delete(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    /***
     * 修改Brand数据
     * @param brand
     * @param id
     * @return
     */
    @PutMapping(value = "/{id}")
    public Result update(@RequestBody Brand brand, @PathVariable Integer id) {
        //设置主键值
        brand.setId(id);
        //调用BrandService实现修改Brand
        brandService.update(brand);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    /***
     * 新增Brand数据
     * @param brand
     * @return
     */
    @PostMapping
    public Result add(@RequestBody Brand brand) {
        //调用BrandService实现添加Brand
        brandService.add(brand);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    /***
     * 根据ID查询Brand数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<Brand> findById(@PathVariable Integer id) {
        //调用BrandService实现根据主键查询Brand
        Brand brand = brandService.findById(id);
        return new Result<Brand>(true, StatusCode.OK, "查询成功", brand);
    }


}
