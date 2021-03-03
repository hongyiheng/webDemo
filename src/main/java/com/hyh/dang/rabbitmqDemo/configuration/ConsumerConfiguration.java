package com.hyh.dang.rabbitmqDemo.configuration;

import com.hyh.dang.rabbitmqDemo.config.RabbitMqQueue;
import com.hyh.dang.rabbitmqDemo.consumer.TestConsumer;
import com.hyh.dang.rabbitmqDemo.impl.DefaultRabbitMqRegister;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsumerConfiguration {
    /**
     * 使用 默认的注册器 注册 消息队列的消费者(消息处理器)
     */
    @Bean
    Object listenerRabbit(DefaultRabbitMqRegister rabbitMqRegister) {
        rabbitMqRegister.listenerQueue(testConsumer(), RabbitMqQueue.MQ_QUEUE_TEST);
        return null;
    }
    @Bean
    TestConsumer testConsumer(){
        return new TestConsumer();
    }


}
