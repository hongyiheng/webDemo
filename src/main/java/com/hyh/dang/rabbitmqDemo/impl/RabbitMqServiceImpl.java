package com.hyh.dang.rabbitmqDemo.impl;


import com.hyh.dang.rabbitmqDemo.IRabbitMqExchange;
import com.hyh.dang.rabbitmqDemo.IRabbitMqRouting;
import com.hyh.dang.rabbitmqDemo.IRabbitMqService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 默认的RabbitMQ 实现
 */
public class RabbitMqServiceImpl implements IRabbitMqService, RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {

    private Logger logger = LoggerFactory.getLogger(RabbitMqServiceImpl.class);

    @Autowired
    protected RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init() {
        rabbitTemplate.setConfirmCallback(this::confirm);
        rabbitTemplate.setReturnCallback(this::returnedMessage);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
    }

    @Override
    public void send(IRabbitMqExchange exchange, IRabbitMqRouting routing, Object msg) {
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(exchange.exchangeName(), routing.routingKey(), msg, correlationId);
    }

    @Override
    public void send(IRabbitMqExchange exchange, IRabbitMqRouting routing, Object msg, long delay) {
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        if (delay > 0) {
            MessagePostProcessor processor = (Message message) -> {
                message.getMessageProperties().setExpiration(delay + "");
                //设置Delivery mode 为2
                message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                return message;
            };
            rabbitTemplate.convertAndSend(exchange.delayExchangeName(), routing.routingKey(), msg, processor, correlationId);
        } else {
            MessagePostProcessor processor = (Message message) -> {
                message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                return message;
            };
            rabbitTemplate.convertAndSend(exchange.exchangeName(), routing.routingKey(), msg, processor, correlationId);
        }
    }

    /**
     * 消息发送的回调
     *
     * @param correlationId 消息Id
     * @param ack           是否成功的标示
     * @param cause         错误原因
     */
    @Override
    public void confirm(CorrelationData correlationId, boolean ack, String cause) {
        if (ack) {
            logger.info("消息发送成功 correlationId: {} cause: {}", correlationId, cause);
        } else {
            logger.error("消息发送失败 correlationId: {} cause: {}", correlationId, cause);
        }
    }

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        logger.info("returnedMessage message: {} replyCode: {} exchange: {} routingKey: {}", message, replyCode, exchange, routingKey);
    }

}
