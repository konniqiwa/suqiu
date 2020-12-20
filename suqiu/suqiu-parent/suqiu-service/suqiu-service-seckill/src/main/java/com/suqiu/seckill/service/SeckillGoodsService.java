package com.suqiu.seckill.service;

import com.suqiu.seckill.pojo.SeckillGoods;
import com.github.pagehelper.PageInfo;

import java.util.List;

/****
 * @Author:admin
 * @Description:SeckillGoods业务层接口
 * @Date 2019/6/14 0:16
 *****/
public interface SeckillGoodsService {

    /***
     * SeckillGoods多条件分页查询
     * @param seckillGoods
     * @param page
     * @param size
     * @return
     */
    PageInfo<SeckillGoods> findPage(SeckillGoods seckillGoods, int page, int size);

    /***
     * SeckillGoods分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<SeckillGoods> findPage(int page, int size);

    /***
     * SeckillGoods多条件搜索方法
     * @param seckillGoods
     * @return
     */
    List<SeckillGoods> findList(SeckillGoods seckillGoods);

    /***
     * 删除SeckillGoods
     * @param id
     */
    void delete(Long id);

    /***
     * 修改SeckillGoods数据
     * @param seckillGoods
     */
    void update(SeckillGoods seckillGoods);

    /***
     * 新增SeckillGoods
     * @param seckillGoods
     */
    void add(SeckillGoods seckillGoods);

    /**
     * 根据ID查询SeckillGoods
     * @param id
     * @return
     */
     SeckillGoods findById(Long id);

    /***
     * 查询所有SeckillGoods
     * @return
     */
    List<SeckillGoods> findAll();


    /****
     * URL:/seckill/goods/list
     * 对应时间段秒杀商品集合查询
     * 调用Service查询数据
     * @param time:2019050716
     */
    List<SeckillGoods> list(String time);

    /****
     * 根据ID查询商品详情
     * @param time:时间区间
     * @param id:商品ID
     */
    SeckillGoods one(String time,Long id);
}
