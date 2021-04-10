package com.suqiu.goods.controller;

import com.suqiu.goods.pojo.Category;
import com.suqiu.goods.service.CategoryService;
import com.github.pagehelper.PageInfo;
import com.suqiu.model.req.BasePageModel;
import entity.JsonDTO;
import entity.Result;
import entity.StatusCode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/****
 * @Author:admin
 * @Description:
 * @Date 2019/6/14 0:18
 *****/

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /***
     * Category分页条件搜索实现
     * @param category
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}")
    public Result<PageInfo> findPage(@RequestBody(required = false) Category category, @PathVariable int page, @PathVariable int size) {
        //调用CategoryService实现分页条件查询Category
        PageInfo<Category> pageInfo = categoryService.findPage(category, page, size);
        return new Result(true, StatusCode.OK, "查询成功", pageInfo);
    }

    /***
     * Category分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}")
    public Result<PageInfo> findPage(@PathVariable int page, @PathVariable int size) {
        //调用CategoryService实现分页查询Category
        PageInfo<Category> pageInfo = categoryService.findPage(page, size);
        return new Result<PageInfo>(true, StatusCode.OK, "查询成功", pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param category
     * @return
     */
    @PostMapping(value = "/search")
    public Result<List<Category>> findList(@RequestBody(required = false) Category category) {
        //调用CategoryService实现条件查询Category
        List<Category> list = categoryService.findList(category);
        return new Result<List<Category>>(true, StatusCode.OK, "查询成功", list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable Integer id) {
        //调用CategoryService实现根据主键删除
        categoryService.delete(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    /***
     * 修改Category数据
     * @param category
     * @param id
     * @return
     */
    @PutMapping(value = "/{id}")
    public Result update(@RequestBody Category category, @PathVariable Integer id) {
        //设置主键值
        category.setId(id);
        //调用CategoryService实现修改Category
        categoryService.update(category);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    /***
     * 新增Category数据
     * @param category
     * @return
     */
    @PostMapping
    public Result add(@RequestBody Category category) {
        //调用CategoryService实现添加Category
        categoryService.add(category);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    /***
     * 根据ID查询Category数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<Category> findById(@PathVariable Integer id) {
        //调用CategoryService实现根据主键查询Category
        Category category = categoryService.findById(id);
        return new Result<Category>(true, StatusCode.OK, "查询成功", category);
    }

    /***
     * 查询Category全部数据，树形结构（递归获取子菜单）
     * @return
     */
    @GetMapping
    public JsonDTO findAll() {
        //调用CategoryService实现查询所有Category
        List<Category> listTree = categoryService.findAllTree();
        return JsonDTO.createInstance().setStatus(JsonDTO.SUCCESS).put("list", listTree);
    }

    /**
     * 根据父ID 查询该分类下的所有的子分类列表   如果是一级分类 pid = 0
     *
     * @param pid
     * @return
     */
    @PostMapping("/list/{pid}")
    public JsonDTO findByParentId(@PathVariable(name = "pid") Integer pid, @RequestBody BasePageModel reqModel) {
        //SELECT * from tb_category where parent_id=0
        List<Category> categoryList = categoryService.findByParentId(pid, reqModel);
        PageInfo pageInfo = new PageInfo(categoryList);
        return JsonDTO.createInstance().setStatus(JsonDTO.SUCCESS).put("list", categoryList).put("total", pageInfo.getTotal());
    }

    @GetMapping("/set")
    public JsonDTO setLevel() {
        categoryService.setLevel();
        return JsonDTO.createInstance().setStatus(JsonDTO.SUCCESS);
    }

}
