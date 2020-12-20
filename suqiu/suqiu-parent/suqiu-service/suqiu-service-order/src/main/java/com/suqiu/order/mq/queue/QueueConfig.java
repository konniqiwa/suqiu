package com.suqiu.order.mq.queue;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class QueueConfig {

    /**
     * 创建延时队列
     */
    @Bean
    public Queue orderDelayQueue() {
        return QueueBuilder
                .durable("orderDelayQueue")
                .withArgument("x-dead-letter-exchange","orderListenerExchange")
                .withArgument("x-dead-letter-routing-key","orderLinstenerQueue")
                .build();

    }

    /**
     * 创建监听队列
     */
    @Bean
    public Queue orderLinstenerQueue() {
        return new Queue("orderLinstenerQueue");
    }

    /**
     * 创建交换机
     */
    @Bean
    public Exchange orderListenerExchange() {
        return new DirectExchange("orderListenerExchange");
    }

    /**
     * 绑定交换机和队列
     */
    @Bean
    public Binding orderListenerBinding() {
        return BindingBuilder.bind(orderLinstenerQueue()).to(orderListenerExchange()).with("orderLinstenerQueue").noargs();
    }
}
