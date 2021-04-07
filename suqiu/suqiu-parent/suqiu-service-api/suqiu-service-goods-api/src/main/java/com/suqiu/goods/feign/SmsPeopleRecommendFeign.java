package com.suqiu.goods.feign;
import com.github.pagehelper.PageInfo;
import com.suqiu.goods.pojo.SmsPeopleRecommend;
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
@RequestMapping("/smsPeopleRecommend")
public interface SmsPeopleRecommendFeign {

    /***
     * SmsPeopleRecommend分页条件搜索实现
     * @param smsPeopleRecommend
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}" )
    Result<PageInfo> findPage(@RequestBody(required = false) SmsPeopleRecommend smsPeopleRecommend, @PathVariable int page, @PathVariable int size);

    /***
     * SmsPeopleRecommend分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}" )
    Result<PageInfo> findPage(@PathVariable int page, @PathVariable int size);

    /***
     * 多条件搜索品牌数据
     * @param smsPeopleRecommend
     * @return
     */
    @PostMapping(value = "/search" )
    Result<List<SmsPeopleRecommend>> findList(@RequestBody(required = false) SmsPeopleRecommend smsPeopleRecommend);

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}" )
    Result delete(@PathVariable Long id);

    /***
     * 修改SmsPeopleRecommend数据
     * @param smsPeopleRecommend
     * @param id
     * @return
     */
    @PutMapping(value="/{id}")
    Result update(@RequestBody SmsPeopleRecommend smsPeopleRecommend, @PathVariable Long id);

    /***
     * 新增SmsPeopleRecommend数据
     * @param smsPeopleRecommend
     * @return
     */
    @PostMapping
    Result add(@RequestBody SmsPeopleRecommend smsPeopleRecommend);

    /***
     * 根据ID查询SmsPeopleRecommend数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    Result<SmsPeopleRecommend> findById(@PathVariable Long id);

    /***
     * 查询SmsPeopleRecommend全部数据
     * @return
     */
    @GetMapping
    Result<List<SmsPeopleRecommend>> findAll();
}