package com.suqiu.order.service;

import com.suqiu.order.pojo.OrderItem;

import java.util.List;

public interface CartService {

    /**
     * 加入购物车
     * @param num
     * @param id
     * @param username
     */
    void add(Integer num, Long id,String username);

    /**
     * 查询购物车列表
     */

    List<OrderItem> list(String username);
}
