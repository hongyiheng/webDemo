package com.hyh.dang.rabbitmqDemo.impl;

import com.hyh.dang.rabbitmqDemo.*;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.SmartLifecycle;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 默认的RabbitMq 注册
 */
public class DefaultRabbitMqRegister implements IRabbitMqRegister, SmartLifecycle {

    ConnectionFactory connectionFactory;

    Channel channel;

    public DefaultRabbitMqRegister() {
    }

    public DefaultRabbitMqRegister(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @PostConstruct
    public void init() {
        channel = connectionFactory.createConnection().createChannel(false);
    }

    @Override
    public void registerExchange(IRabbitMqExchange... exchanges) throws IOException {
        for (IRabbitMqExchange exchange : exchanges) {
            channel.exchangeDeclare(exchange.exchangeName(), exchange.type(), exchange.durable(), exchange.autoDelete(), exchange.internal(), exchange.arguments());
        }
    }

    @Override
    public void registerQueue(IRabbitMqQueue... queues) throws IOException {
        for (IRabbitMqQueue queue : queues) {
            channel.queueDeclare(queue.queueName(), queue.durable(), queue.exclusive(), queue.autoDelete(), queue.arguments());
        }
    }

    @Override
    public void registerBinding(IRabbitMqBinding... bindings) throws IOException {
        for (IRabbitMqBinding binding : bindings) {
            channel.queueBind(binding.queue().queueName(), binding.exchange().exchangeName(), binding.routing().routingKey());
            if (binding.allowDelay()) {
                registerDelayBinding(binding);
            }
        }
    }

    /**
     * 创建一个内部的 死信队列 用来实现 延时队列
     */
    private void registerDelayBinding(IRabbitMqBinding binding) throws IOException {
        IRabbitMqExchange exchange = binding.exchange();
        // 注册一个延时的消息交换机
        channel.exchangeDeclare(exchange.delayExchangeName(), exchange.type(), exchange.durable(), exchange.autoDelete(), exchange.internal(), exchange.arguments());
        // 注册一个死信队列  设置消息超时后，将消息转发到原来的Router队列
        IRabbitMqQueue queue = binding.queue();
        Map<String, Object> arguments = queue.arguments();
        if (arguments == null) {
            arguments = new HashMap<>(4);
        }
        arguments.put("x-dead-letter-exchange", binding.exchange().exchangeName());
        arguments.put("x-dead-letter-routing-key", binding.routing().routingKey());
        // 声明队列 第二个参durable：是否要持久化；
        // 第三个参exclusive：如果设置true的化，队列将变成私有的，只有创建队列的应用程序才能够消费队列消息；
        // 第四个参auto-delete：当最后一个消费者取消订阅的时候，队列会自动移除；
        // 第五个参arguments：加上"x-dead-letter-exchange"，"x-dead-letter-routing-key" 绑定交换机就是死信队列
        channel.queueDeclare(queue.delayQueueName(), queue.durable(), queue.exclusive(), queue.autoDelete(), arguments);
        // 将交换机和队列绑定
        channel.queueBind(queue.delayQueueName(), exchange.delayExchangeName(), binding.routing().routingKey());
    }

    private List<MessageListenerContainer> listenerContainers = new LinkedList<>();

    @Override
    public void listenerQueue(IRabbitMqListener listener, IRabbitMqQueue... queues) {
        String[] queueNames = new String[queues.length];
        for (int idx = 0; idx < queues.length; idx++) {
            queueNames[idx] = queues[idx].queueName();
        }
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        // 配置手动确认
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        container.setQueueNames(queueNames);
        container.setMessageListener((MessageListener) listener);
        listenerContainers.add(container);
    }

    @Override
    public void start() {
        for (MessageListenerContainer container : listenerContainers) {
            container.start();
        }
    }

    @Override
    public void stop() {
    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public boolean isAutoStartup() {
        return true;
    }

    @Override
    public void stop(Runnable runnable) {
    }

    @Override
    public int getPhase() {
        return 9999;
    }
}
