package com.suqiu.goods.controller;

import com.github.pagehelper.PageInfo;
import com.suqiu.goods.pojo.SmsBrandRecommend;
import com.suqiu.goods.service.SmsBrandRecommendService;
import com.suqiu.model.req.CreateNewRecommendModel;
import com.suqiu.model.req.CreatePeopleRecommendModel;
import com.suqiu.model.req.NewRecommendModel;
import com.suqiu.model.req.PeopleRecommendModel;
import com.suqiu.model.res.SmsNewRecommendListDTO;
import com.suqiu.model.res.SmsPeopleRecommendListDTO;
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
@RequestMapping("/smsBrandRecommend")
@CrossOrigin
public class SmsBrandRecommendController {

    @Autowired
    private SmsBrandRecommendService smsBrandRecommendService;

    @PostMapping("/createBrand")
    public JsonDTO createNew(@RequestBody List<CreateNewRecommendModel> brandRecommend) {
        smsBrandRecommendService.create(brandRecommend);
        return JsonDTO.createInstance().setStatus(JsonDTO.SUCCESS).setMsg("添加品牌推荐成功");
    }

    @PostMapping(value = "/deleteBrand")
    public JsonDTO deleteNew(List<Long> ids) {
        //调用SmsPeopleRecommendService实现根据主键删除
        smsBrandRecommendService.delete(ids);
        return JsonDTO.createInstance().setStatus(JsonDTO.SUCCESS).setMsg("删除品牌推荐");
    }

    @PostMapping("/sortBrand/{id}")
    public JsonDTO newSort(@PathVariable Long id, int sort) {
        smsBrandRecommendService.peopleSort(id, sort);
        return JsonDTO.createInstance().setStatus(JsonDTO.SUCCESS).setMsg("设置品牌排序");
    }

    @PostMapping("/isBrandRecommend")
    public JsonDTO isNewRecommend(List<Long> ids, int recommendStatus) {
        smsBrandRecommendService.isPeopleRecommend(ids, recommendStatus);
        return JsonDTO.createInstance().setStatus(JsonDTO.SUCCESS).setMsg("设置品牌推荐状态");
    }

    @PostMapping("/listBrand")
    public JsonDTO smsNewRecommendList(@RequestBody NewRecommendModel model) {
        SmsNewRecommendListDTO smsNewRecommendListDTO = smsBrandRecommendService.smsPeopleRecommendList(model);
        return JsonDTO.createInstance().setStatus(JsonDTO.SUCCESS).setMsg("查询品牌推荐列表").setData(smsNewRecommendListDTO);
    }

    /***
     * SmsBrandRecommend分页条件搜索实现
     * @param smsBrandRecommend
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@RequestBody(required = false)  SmsBrandRecommend smsBrandRecommend, @PathVariable  int page, @PathVariable  int size){
        //调用SmsBrandRecommendService实现分页条件查询SmsBrandRecommend
        PageInfo<SmsBrandRecommend> pageInfo = smsBrandRecommendService.findPage(smsBrandRecommend, page, size);
        return new Result(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * SmsBrandRecommend分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用SmsBrandRecommendService实现分页查询SmsBrandRecommend
        PageInfo<SmsBrandRecommend> pageInfo = smsBrandRecommendService.findPage(page, size);
        return new Result<PageInfo>(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param smsBrandRecommend
     * @return
     */
    @PostMapping(value = "/search" )
    public Result<List<SmsBrandRecommend>> findList(@RequestBody(required = false)  SmsBrandRecommend smsBrandRecommend){
        //调用SmsBrandRecommendService实现条件查询SmsBrandRecommend
        List<SmsBrandRecommend> list = smsBrandRecommendService.findList(smsBrandRecommend);
        return new Result<List<SmsBrandRecommend>>(true,StatusCode.OK,"查询成功",list);
    }


    /***
     * 修改SmsBrandRecommend数据
     * @param smsBrandRecommend
     * @param id
     * @return
     */
    @PutMapping(value="/{id}")
    public Result update(@RequestBody  SmsBrandRecommend smsBrandRecommend,@PathVariable Long id){
        //设置主键值
        smsBrandRecommend.setBrandId(id);
        //调用SmsBrandRecommendService实现修改SmsBrandRecommend
        smsBrandRecommendService.update(smsBrandRecommend);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /***
     * 新增SmsBrandRecommend数据
     * @param smsBrandRecommend
     * @return
     */
    @PostMapping
    public Result add(@RequestBody   SmsBrandRecommend smsBrandRecommend){
        //调用SmsBrandRecommendService实现添加SmsBrandRecommend
        smsBrandRecommendService.add(smsBrandRecommend);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /***
     * 根据ID查询SmsBrandRecommend数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<SmsBrandRecommend> findById(@PathVariable Long id){
        //调用SmsBrandRecommendService实现根据主键查询SmsBrandRecommend
        SmsBrandRecommend smsBrandRecommend = smsBrandRecommendService.findById(id);
        return new Result<SmsBrandRecommend>(true,StatusCode.OK,"查询成功",smsBrandRecommend);
    }

    /***
     * 查询SmsBrandRecommend全部数据
     * @return
     */
    @GetMapping
    public Result<List<SmsBrandRecommend>> findAll(){
        //调用SmsBrandRecommendService实现查询所有SmsBrandRecommend
        List<SmsBrandRecommend> list = smsBrandRecommendService.findAll();
        return new Result<List<SmsBrandRecommend>>(true, StatusCode.OK,"查询成功",list) ;
    }
}
