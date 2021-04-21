package com.suqiu.order.service;

import com.suqiu.model.req.CloseOrderModel;
import com.suqiu.model.req.DeleteOrderModel;
import com.suqiu.model.req.DeliveryOrderModel;
import com.suqiu.order.pojo.Order;
import com.github.pagehelper.PageInfo;

import java.util.List;

/****
 * @Author:admin
 * @Description:Order业务层接口
 * @Date 2019/6/14 0:16
 *****/
public interface OrderService {

    /***
     * Order多条件分页查询
     * @param order
     * @param page
     * @param size
     * @return
     */
    PageInfo<Order> findPage(Order order, int page, int size);

    /***
     * Order分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<Order> findPage(int page, int size);

    /***
     * Order多条件搜索方法
     * @param order
     * @return
     */
    List<Order> findList(Order order);

    /***
     * 删除Order
     */
    void delete(DeleteOrderModel model);

    /***
     * 修改Order数据
     * @param order
     */
    void update(Order order);

    /***
     * 新增Order
     * @param order
     */
    void add(Order order);

    /**
     * 根据ID查询Order
     *
     * @param id
     * @return
     */
    Order findById(String id);

    /***
     * 查询所有Order
     * @return
     */
    List<Order> findAll();

    /**
     * 更新订单状态
     *
     * @param outtradeno
     * @param transaction_id
     */
    void updateStatus(String outtradeno, String paytime, String transaction_id) throws Exception;

    /**
     * 删除订单
     *
     * @param outtradeno
     */
    void deleteOrder(String outtradeno);


    /**
     * 关闭订单
     *
     * @param closeOrderModel
     */
    void closeOrder(CloseOrderModel closeOrderModel);

    /**
     * 批量发货
     *
     * @param model
     */
    void delivery(DeliveryOrderModel model);
}
