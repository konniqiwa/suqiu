package com.changgou.order.controller;

import com.changgou.order.pojo.OrderItem;
import com.changgou.order.service.CartService;
import com.mysql.cj.x.protobuf.Mysqlx;
import entity.Result;
import entity.StatusCode;
import entity.TokenDecode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping(value = "/cart")
public class CartController {

    @Autowired
    private CartService cartService;


    /**
     * 购物车列表查询
     * @return
     */
    @GetMapping(value = "/list")
    public Result list() {

        Map<String, String> userInfo = TokenDecode.getUserInfo();
        System.out.println(userInfo);
        String username = userInfo.get("username");

        List<OrderItem> list = cartService.list(username);
        return new Result(true,StatusCode.OK,"购物车列表查询成功",list);
    }

    /**
     * 加入购物车
     * @param num:商品数量
     * @param id：商品id
     * @return
     */
    @GetMapping(value = "/add")
    public Result add(Integer num, Long id) {

        Map<String, String> userInfo = TokenDecode.getUserInfo();
        System.out.println(userInfo);
        String username = userInfo.get("username");

        cartService.add(num,id,username);

        return new Result(true, StatusCode.OK,"加入购物车成功");

    }
}
