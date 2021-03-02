package com.hyh.dang.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * 消费消费者
 */
@Configuration
public class RabbitConfig {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public static final String EXCHANGE = "amq.direct";

    public static final String QUEUE_A = "test";

    public static final String ROUTINGKEY_A = "test_A";

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("159.75.140.121", 5672);
        connectionFactory.setUsername("hyh");
        connectionFactory.setPassword("hyh804002");
        connectionFactory.setVirtualHost("/");
        connectionFactory.setPublisherConfirms(true);//必须要设置，才能进行消息的回调。
        return connectionFactory;
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    //必须是prototype类型
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        return template;
    }

    /**
     * 针对消费者配置
     * 1. 设置交换机类型
     * 2. 将队列绑定到交换机
     * FanoutExchange: 将消息分发到所有的绑定队列，无routingkey的概念
     * HeadersExchange ：通过添加属性key-value匹配
     * DirectExchange:按照routingkey分发到指定队列
     * TopicExchange:多关键字匹配
     */
    @Bean
    public DirectExchange defaultExchange() {
        return new DirectExchange(EXCHANGE);
    }

    /**
     * 获取队列A
     *
     * @return
     */
    @Bean
    public Queue queueA() {
        return new Queue(QUEUE_A, true); //队列持久
    }


    /**
     * 队列和交换机绑定
     *
     * @return
     */
    @Bean
    public Binding bindingA() {
        return BindingBuilder.bind(queueA()).to(defaultExchange()).with(RabbitConfig.ROUTINGKEY_A);
    }


    /**
     * 发送方不需要监听队列，注释掉此方法，接收方则需要放开，并实现如 setMessageListener中的onMessage()方法
     * 具体可看 OrderReceiver ，但发送消费双方都要此配置类
     * @return
     */
//    @Bean
//    public SimpleMessageListenerContainer getMessageContainer() {
//        //加载处理消息A的队列
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory());
//        //设置接收多个队列里面的消息，这里设置接收队列A
//        //假如想一个消费者处理多个队列里面的信息可以如下设置：
//        //container.setQueues(queueA(),queueB(),queueC());
//        container.setQueues(queueA());
//        container.setExposeListenerChannel(true);
//        //设置最大的并发的消费者数量
//        container.setMaxConcurrentConsumers(1);
//        //最小的并发消费者的数量
//        container.setConcurrentConsumers(1);
//        //设置确认模式手工确认
//        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
//
//        return container;
//    }

}
