package com.suqiu.order.mq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@RabbitListener(queues = "orderLinstenerQueue")
public class DelayMessageListener {

    /**
     * 延时队列监听
     * @param message
     */
    @RabbitHandler
    public void getDelayMessage(String message) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = simpleDateFormat.format(new Date());
        System.out.println("监听消息的时间：" + date);
        System.out.println("监听到的信息：" + message);

    }
}
