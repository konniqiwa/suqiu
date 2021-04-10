package com.suqiu.goods.feign;
import com.github.pagehelper.PageInfo;
import com.suqiu.goods.pojo.SmsBrandRecommend;
import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/****
 * @Author:admin
 * @Description:
 * @Date 2019/6/18 13:58
 *****/
@FeignClient(name="goods")
@RequestMapping("/smsBrandRecommend")
public interface SmsBrandRecommendFeign {

    /***
     * SmsBrandRecommend分页条件搜索实现
     * @param smsBrandRecommend
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}" )
    Result<PageInfo> findPage(@RequestBody(required = false) SmsBrandRecommend smsBrandRecommend, @PathVariable int page, @PathVariable int size);

    /***
     * SmsBrandRecommend分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}" )
    Result<PageInfo> findPage(@PathVariable int page, @PathVariable int size);

    /***
     * 多条件搜索品牌数据
     * @param smsBrandRecommend
     * @return
     */
    @PostMapping(value = "/search" )
    Result<List<SmsBrandRecommend>> findList(@RequestBody(required = false) SmsBrandRecommend smsBrandRecommend);

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}" )
    Result delete(@PathVariable Long id);

    /***
     * 修改SmsBrandRecommend数据
     * @param smsBrandRecommend
     * @param id
     * @return
     */
    @PutMapping(value="/{id}")
    Result update(@RequestBody SmsBrandRecommend smsBrandRecommend, @PathVariable Long id);

    /***
     * 新增SmsBrandRecommend数据
     * @param smsBrandRecommend
     * @return
     */
    @PostMapping
    Result add(@RequestBody SmsBrandRecommend smsBrandRecommend);

    /***
     * 根据ID查询SmsBrandRecommend数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    Result<SmsBrandRecommend> findById(@PathVariable Long id);

    /***
     * 查询SmsBrandRecommend全部数据
     * @return
     */
    @GetMapping
    Result<List<SmsBrandRecommend>> findAll();
}