package com.hyh.dang.rabbitmqDemo.consumer;

import com.hyh.dang.rabbitmqDemo.impl.AbstractMessageListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestConsumer extends AbstractMessageListener {

    Logger logger = LoggerFactory.getLogger(TestConsumer.class);
    @Override
    public boolean handleMessage(Object obj) {
        logger.info("rabbitmq消费者开始消费，消息内容：" +obj.toString());
        return true;
    }
}
