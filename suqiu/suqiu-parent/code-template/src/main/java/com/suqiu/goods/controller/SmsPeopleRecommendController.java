package com.suqiu.goods.controller;
import com.suqiu.goods.pojo.SmsPeopleRecommend;
import com.suqiu.goods.service.SmsPeopleRecommendService;
import com.github.pagehelper.PageInfo;
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
@RequestMapping("/smsPeopleRecommend")
@CrossOrigin
public class SmsPeopleRecommendController {

    @Autowired
    private SmsPeopleRecommendService smsPeopleRecommendService;

    /***
     * SmsPeopleRecommend分页条件搜索实现
     * @param smsPeopleRecommend
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@RequestBody(required = false)  SmsPeopleRecommend smsPeopleRecommend, @PathVariable  int page, @PathVariable  int size){
        //调用SmsPeopleRecommendService实现分页条件查询SmsPeopleRecommend
        PageInfo<SmsPeopleRecommend> pageInfo = smsPeopleRecommendService.findPage(smsPeopleRecommend, page, size);
        return new Result(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * SmsPeopleRecommend分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用SmsPeopleRecommendService实现分页查询SmsPeopleRecommend
        PageInfo<SmsPeopleRecommend> pageInfo = smsPeopleRecommendService.findPage(page, size);
        return new Result<PageInfo>(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param smsPeopleRecommend
     * @return
     */
    @PostMapping(value = "/search" )
    public Result<List<SmsPeopleRecommend>> findList(@RequestBody(required = false)  SmsPeopleRecommend smsPeopleRecommend){
        //调用SmsPeopleRecommendService实现条件查询SmsPeopleRecommend
        List<SmsPeopleRecommend> list = smsPeopleRecommendService.findList(smsPeopleRecommend);
        return new Result<List<SmsPeopleRecommend>>(true,StatusCode.OK,"查询成功",list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable Long id){
        //调用SmsPeopleRecommendService实现根据主键删除
        smsPeopleRecommendService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /***
     * 修改SmsPeopleRecommend数据
     * @param smsPeopleRecommend
     * @param id
     * @return
     */
    @PutMapping(value="/{id}")
    public Result update(@RequestBody  SmsPeopleRecommend smsPeopleRecommend,@PathVariable Long id){
        //设置主键值
        smsPeopleRecommend.setProductId(id);
        //调用SmsPeopleRecommendService实现修改SmsPeopleRecommend
        smsPeopleRecommendService.update(smsPeopleRecommend);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /***
     * 新增SmsPeopleRecommend数据
     * @param smsPeopleRecommend
     * @return
     */
    @PostMapping
    public Result add(@RequestBody   SmsPeopleRecommend smsPeopleRecommend){
        //调用SmsPeopleRecommendService实现添加SmsPeopleRecommend
        smsPeopleRecommendService.add(smsPeopleRecommend);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /***
     * 根据ID查询SmsPeopleRecommend数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<SmsPeopleRecommend> findById(@PathVariable Long id){
        //调用SmsPeopleRecommendService实现根据主键查询SmsPeopleRecommend
        SmsPeopleRecommend smsPeopleRecommend = smsPeopleRecommendService.findById(id);
        return new Result<SmsPeopleRecommend>(true,StatusCode.OK,"查询成功",smsPeopleRecommend);
    }

    /***
     * 查询SmsPeopleRecommend全部数据
     * @return
     */
    @GetMapping
    public Result<List<SmsPeopleRecommend>> findAll(){
        //调用SmsPeopleRecommendService实现查询所有SmsPeopleRecommend
        List<SmsPeopleRecommend> list = smsPeopleRecommendService.findAll();
        return new Result<List<SmsPeopleRecommend>>(true, StatusCode.OK,"查询成功",list) ;
    }
}
