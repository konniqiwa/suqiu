package com.suqiu.order.task;

import com.suqiu.order.pojo.Order;
import com.suqiu.order.service.OrderConfigService;
import com.suqiu.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.stream.Collectors;

/**
 * @author suqiu
 */
@Slf4j
@Component
public class OrderOperateTask {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderConfigService orderConfigService;

    /**
     * 超时未支付
     */
    @Scheduled(cron = "0 0/1 * *")
    public void orderOvertime() {
        Order order1 = new Order();
        orderService.findAll().stream()
                .filter(order -> (
                                System.currentTimeMillis() - order.getCreateTime().getTime() > orderConfigService.findAll().get(0).getOrderTimeout() * 60 * 1000 && "0".equals(order.getPayStatus())
                        )
                )
                .collect(Collectors.toList())
                .forEach(order -> {
                    // "1"订单状态设置为已完成
                    order.setOrderStatus("1");
                    order.setUpdateTime(new Date());
                    order.setCloseTime(new Date());
                    orderService.update(order);
                    log.info("订单超时未支付，自动关闭   orderId: " + order.getId());
                });

    }

    /**
     * 超时未收货
     */
    @Scheduled(cron = "0 0/1 * *")
    public void takeOvertime() {
        orderService.findAll().stream()
                .filter(order -> (
                                order.getConsignTime() != null && order.getOrderStatus().equals("0") && System.currentTimeMillis() - order.getConsignTime().getTime() > orderConfigService.findAll().get(0).getOrderTimeout() * 24 * 60 * 1000
                        )
                )
                .collect(Collectors.toList())
                .forEach(order -> {
                    // "1"订单状态设置为已完成
                    order.setOrderStatus("1");
                    order.setUpdateTime(new Date());
                    order.setEndTime(new Date());
                    orderService.update(order);
                    log.info("订单超时未收货，自动关闭   orderId: " + order.getId());
                });
    }

    /**
     * 申请售后时限
     */
    @Scheduled(cron = "0 0/1 * *")
    public void serviceOvertime() {
        orderService.findAll().stream()
                .filter(order -> (
                                order.getEndTime() != null && order.getOrderStatus().equals("1") && System.currentTimeMillis() - order.getEndTime().getTime() > orderConfigService.findAll().get(0).getServiceTimeout() * 24 * 60 * 1000
                        )
                )
                .collect(Collectors.toList())
                .forEach(order -> {
                    order.setOrderStatus("2");
                    orderService.update(order);
                    log.info("订单完成后超过申请售后实现，不能申请售后   orderId: " + order.getId());
                });
    }

    /**
     * 自动五星好评
     */
    @Scheduled(cron = "0 0/1 * *")
    public void commentOvertime() {
        orderService.findAll().stream()
                .filter(order -> (
                                order.getEndTime() != null && System.currentTimeMillis() - order.getEndTime().getTime() > orderConfigService.findAll().get(0).getServiceTimeout() * 24 * 60 * 1000
                        )
                )
                .collect(Collectors.toList())
                .forEach(order -> {
                    order.setBuyerRate("5");
                    orderService.update(order);
                    log.info("订单完成一段时间后，自动五星好评   orderId: " + order.getId());
                });
    }

}
