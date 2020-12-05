package com.changgou.seckill.mq.listener;

import com.alibaba.fastjson.JSON;
import com.changgou.seckill.mq.QueueConfig;
import com.changgou.seckill.service.SeckillOrderService;
import entity.SeckillStatus;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@RabbitListener(queues = QueueConfig.QUEUE_MESSAGE)
public class MessageListener {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private SeckillOrderService seckillOrderService;




    /***
     * 监听消息
     * @param message
     */
    @RabbitHandler
    public void msg(@Payload String message){
        SeckillStatus seckillStatus = JSON.parseObject(message, SeckillStatus.class);

        //如果用户没有排队抢单信息，则不需要操作，如果有排队信息，则关闭订单并且回滚
        Object userQueueStatus = redisTemplate.boundHashOps("UserQueueStatus").get(seckillStatus.getUsername());
        if (userQueueStatus != null) {
            //1.关闭微信支付
            //2.关闭订单，库存回滚
            seckillOrderService.closeOrder(seckillStatus.getUsername());
        }

    }

}