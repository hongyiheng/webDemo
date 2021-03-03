package com.hyh.dang.rabbitmqDemo.config;


import com.hyh.dang.rabbitmqDemo.IRabbitMqBinding;
import com.hyh.dang.rabbitmqDemo.IRabbitMqExchange;
import com.hyh.dang.rabbitmqDemo.IRabbitMqQueue;
import com.hyh.dang.rabbitmqDemo.IRabbitMqRouting;

/**
 * RabbitMq Exchange(交换机) Routing(路由) Queue(队列) 的绑定关系
 * */
public enum RabbitMqBinding implements IRabbitMqBinding {

    MQ_BINDING_TEST(RabbitMqExchange.MQ_EXCHANGE_TEST,RabbitMqRouting.MQ_ROUTING_TEST,RabbitMqQueue.MQ_QUEUE_TEST,true);

    /**
     * exchange(交换机)
     */
    IRabbitMqExchange exchange;
    /**
     * routing(路由)
     */
    IRabbitMqRouting routing;
    /**
     * queue(队列)
     */
    IRabbitMqQueue queue;
    /**
     * 是否允许延时
     */
    boolean allowDelay = false;

    RabbitMqBinding(IRabbitMqExchange exchange,IRabbitMqRouting routing,IRabbitMqQueue queue){
        this.exchange = exchange;
        this.routing = routing;
        this.queue = queue;
    }

    RabbitMqBinding(IRabbitMqExchange exchange,IRabbitMqRouting routing,IRabbitMqQueue queue,boolean allowDelay){
        this.exchange = exchange;
        this.routing = routing;
        this.queue = queue;
        this.allowDelay = allowDelay;
    }

    @Override
    public IRabbitMqExchange exchange() {
        return this.exchange;
    }

    @Override
    public IRabbitMqRouting routing() {
        return this.routing;
    }

    @Override
    public IRabbitMqQueue queue() {
        return this.queue;
    }

    @Override
    public boolean allowDelay() {
        return this.allowDelay;
    }
}
