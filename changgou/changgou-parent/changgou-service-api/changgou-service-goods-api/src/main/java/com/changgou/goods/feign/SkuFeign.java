package com.changgou.goods.feign;

import com.changgou.goods.pojo.Sku;
import entity.Result;
import entity.StatusCode;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 描述
 *
 * @author www.itheima.com
 * @version 1.0
 * @package com.changgou.goods.feign *
 * @since 1.0
 */
@FeignClient(value="goods")
@RequestMapping(value = "/sku")
public interface SkuFeign {

    /**
     * 商品库存递减
     * @param decrmap
     * @return
     */
    @GetMapping(value = "/decr/count")
    public Result decrCount(@RequestParam Map<String,String> decrmap);
    /***
     * 查询Sku全部数据
     * @return
     */
    @GetMapping
    public Result<List<Sku>> findAll();

    /**
     * 根据条件搜索
     * @param sku
     * @return
     */
    @PostMapping(value = "/search" )
    public Result<List<Sku>> findList(@RequestBody(required = false) Sku sku);

    /***
     * 根据ID查询Sku数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<Sku> findById(@PathVariable Long id);
}
