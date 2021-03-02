package com.hyh.dang.rabbitmq.Sender;

import com.alibaba.fastjson.JSON;
import com.hyh.dang.rabbitmq.Order;
import com.hyh.dang.rabbitmq.RabbitConfig;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <br>
 * 标题: 订单消息发送者<br>
 * 描述: 订单消息发送者<br>
 */
@Component
public class OrderSender implements RabbitTemplate.ConfirmCallback{

    private RabbitTemplate rabbitTemplate;

    /**
     * 构造方法注入
     */
    @Autowired
    public OrderSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        rabbitTemplate.setConfirmCallback(this); //rabbitTemplate如果为单例的话，那回调就是最后设置的内容
    }

    /**
     * 发送订单
     *
     * @param order 订单
     * @throws Exception 异常
     */
    public void sendMsg(Order order) {
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(order.getMessageId());
        System.out.println("消息id:" + order.getMessageId());
        // exchange：交换机
        // routingKey：路由键
        // message：消息体内容
        // correlationData：消息唯一ID
        this.rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE, RabbitConfig.ROUTINGKEY_A, JSON.toJSONString(order), correlationData);
    }



    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        System.out.println("回调id:" + correlationData);
        if (ack) {
            System.out.println("消息成功消费");
        } else {
            System.out.println("消息消费失败:" + cause);
        }
    }
}