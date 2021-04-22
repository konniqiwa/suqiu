package com.suqiu.goods.controller;

import com.suqiu.goods.model.req.AuditSpuModel;
import com.suqiu.goods.model.req.LogicDeleteSpuModel;
import com.suqiu.goods.model.req.PullSpuModel;
import com.suqiu.goods.pojo.Goods;
import com.suqiu.goods.pojo.Spu;
import com.suqiu.goods.service.SpuService;
import com.github.pagehelper.PageInfo;
import com.suqiu.model.req.SpuListModel;
import com.suqiu.model.req.UpdateStatusModel;
import com.suqiu.model.res.SpuListDTO;
import com.suqiu.model.res.SpuListTotalDTO;
import com.suqiu.model.res.SpuSpecDTO;
import com.suqiu.model.res.SpuSpecParamDTO;
import entity.JsonDTO;
import entity.Result;
import entity.StatusCode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/****
 * @Author:suqiu
 * @Description: spu控制层
 *****/

@RestController
@RequestMapping("/spu")
public class SpuController {

    @Autowired
    private SpuService spuService;

    @GetMapping("/{id}")
    public JsonDTO getSpuSpecParam(@PathVariable Long id) {
        List<SpuSpecParamDTO> list = spuService.getSpuSpecParam(id);
        return JsonDTO.createInstance().setStatus(JsonDTO.SUCCESS).setMsg("获取sku参数").setData(list);
    }


    @GetMapping("/getSpuSpec/{id}")
    public JsonDTO getSpuSpec(@PathVariable(name = "id") Long id) {
        List<SpuSpecDTO> spuSpec = spuService.getSpuSpec(id);
        return JsonDTO.createInstance().setStatus(JsonDTO.SUCCESS).setMsg("获取商品规格成功").put("list", spuSpec);
    }


    @PostMapping("/updateStatus")
    public JsonDTO updateStatus(UpdateStatusModel reqModel) {
        spuService.updateStatus(reqModel);
        return JsonDTO.createInstance().setStatus(JsonDTO.SUCCESS).setMsg("更改推荐状态成功");
    }

    @GetMapping("/getInfo{id}")
    public JsonDTO getInfo(@PathVariable(name = "id") Long id) {
        SpuListDTO info = spuService.getInfo(id);
        return JsonDTO.createInstance().setStatus(JsonDTO.SUCCESS).setData(info);
    }

    /***
     * Spu分页条件搜索实现
     * @param spu
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}")
    public Result<PageInfo> findPage(@RequestBody(required = false) Spu spu, @PathVariable int page, @PathVariable int size) {
        //调用SpuService实现分页条件查询Spu
        PageInfo<Spu> pageInfo = spuService.findPage(spu, page, size);
        return new Result(true, StatusCode.OK, "查询成功", pageInfo);
    }

    /***
     * Spu分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}")
    public Result<PageInfo> findPage(@PathVariable int page, @PathVariable int size) {
        //调用SpuService实现分页查询Spu
        PageInfo<Spu> pageInfo = spuService.findPage(page, size);
        return new Result<PageInfo>(true, StatusCode.OK, "查询成功", pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param spu
     * @return
     */
    @PostMapping(value = "/search")
    public Result<List<Spu>> findList(@RequestBody(required = false) Spu spu) {
        //调用SpuService实现条件查询Spu
        List<Spu> list = spuService.findList(spu);
        return new Result<List<Spu>>(true, StatusCode.OK, "查询成功", list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable Long id) {
        //调用SpuService实现根据主键删除
        spuService.delete(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    /***
     * 修改Spu数据
     * @param spu
     * @param id
     * @return
     */
    @PutMapping(value = "/{id}")
    public Result update(@RequestBody Spu spu, @PathVariable Long id) {
        //设置主键值
        spu.setId(id);
        //调用SpuService实现修改Spu
        spuService.update(spu);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    /***
     * 新增Spu数据
     * @param spu
     * @return
     */
    @PostMapping
    public Result add(@RequestBody Spu spu) {
        //调用SpuService实现添加Spu
        spuService.add(spu);
        return new Result(true, StatusCode.OK, "添加成功");
    }


    /***
     * 查询Spu全部数据
     * @return
     */
    @GetMapping
    public Result<List<Spu>> findAll() {
        //调用SpuService实现查询所有Spu
        List<Spu> list = spuService.findAll();
        return new Result<List<Spu>>(true, StatusCode.OK, "查询所有商品成功", list);
    }

    /**
     * Goods(SPU+SKU)增加方法详情
     */
    @PostMapping("/save")
    public Result save(@RequestBody Goods goods) {
        spuService.save(goods);
        return new Result(true, StatusCode.OK, "保存商品成功", null);
    }

    //根据点击到的商品(SPU)的ID 获取到GOODS数据返回给页面展示
    @GetMapping("/goods/{id}")
    public Result<Goods> findGoodsById(@PathVariable(value = "id") Long id) {
        Goods goods = spuService.findGoodsById(id);
        return new Result<Goods>(true, StatusCode.OK, "查询goods数据成功", goods);
    }


    /**
     * //审核商品 上架
     *
     * @return
     */
    @PostMapping("/updateIsMarketable")
    public JsonDTO auditSpu(@RequestParam List<Long> ids, @RequestParam int publishStatus) {
        spuService.auditSpu(ids, publishStatus);
        return JsonDTO.createInstance().setStatus(JsonDTO.SUCCESS).setMsg("设置商品上下架状态");
    }

    @PostMapping("/updateNotMarketable")
    public JsonDTO pullSpu(@RequestBody PullSpuModel model) {
        spuService.pullSpu(model);
        return JsonDTO.createInstance().setStatus(JsonDTO.SUCCESS).setMsg("商品下架");
    }

    @PostMapping("/deleteSpu")
    public JsonDTO logicDeleteSpu(@RequestParam List<Long> ids, @RequestParam int deleteStatus) {
        spuService.logicDeleteSpu(ids, deleteStatus);
        return JsonDTO.createInstance().setStatus(JsonDTO.SUCCESS).setMsg("逻辑删除商品");
    }

    @PutMapping("/restore/{id}")
    public Result restore(@PathVariable(name = "id") Long id) {
        spuService.restoreSpu(id);
        return new Result(true, StatusCode.OK, "还原成功");
    }

    @PostMapping("/list")
    public JsonDTO getAllItem(@RequestBody SpuListModel reqModel) throws Exception {
        SpuListTotalDTO bySearch = spuService.findBySearch(reqModel);
        return JsonDTO.createInstance().setStatus(JsonDTO.SUCCESS).setData(bySearch);
    }


}
