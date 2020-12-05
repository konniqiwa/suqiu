package com.changgou.seckill.mq.listener;

import com.alibaba.fastjson.JSON;
import com.changgou.seckill.service.SeckillOrderService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RabbitListener(queues = "${mq.pay.queue.seckillorder}")
public class SeckillOrderPayMessageListener {

    @Autowired
    SeckillOrderService seckillOrderService;


    /**
     * 监听消费消息
     * @param message
     */
    @RabbitHandler
    public void consumeMessage(@Payload String message){
        //将消息转换成Map对象
        Map<String,String> resultMap = JSON.parseObject(message, Map.class);

        String return_code = resultMap.get("return_code");

        if (return_code.equals("SUCCESS")) {
            String out_trade_no = resultMap.get("out_trade_no");
            Map<String,String> attachMap = JSON.parseObject(resultMap.get("attach"), Map.class);

            if (resultMap.get("return_code").equals("SUCCESS")) {
                //修改订单状态
                seckillOrderService.updatePayStatus(out_trade_no,resultMap.get("transaction_id"),attachMap.get("username"));
            }else {
                //删除订单，回滚库存
                //支付失败,删除订单
                seckillOrderService.closeOrder(attachMap.get("username"));
            }
        }
    }
}