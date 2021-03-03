package com.hyh.dang.rabbitmqDemo.config;

import com.hyh.dang.rabbitmqDemo.IRabbitMqQueue;


/**
 * RabbitMq queue(队列)的定义
 * */
public enum RabbitMqQueue implements IRabbitMqQueue {
    MQ_QUEUE_TEST("mq.queue.test");

    private String queueName;

    @Override
    public String queueName() {
        return this.queueName;
    }

    RabbitMqQueue(String queueName){
        this.queueName = queueName;
    }

}
