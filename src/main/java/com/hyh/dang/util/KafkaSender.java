//package com.hyh.dang.util;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.hyh.dang.entity.Message;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//import java.util.UUID;
//
//@Component
//@Slf4j
//public class KafkaSender {
//
//    @Autowired
//    private KafkaTemplate<String, String> kafkaTemplate;
//
//    //发送消息方法
//    public void send() {
//        Message message = new Message();
//        message.setId(System
//                .currentTimeMillis());
//        message.setMsg(UUID.randomUUID().toString());
//        message.setSendTime(new Date());
//        log.info("+++++++++++++++++++++  message = {}", JSON.toJSONString(message));
//        kafkaTemplate.send("zhisheng", JSON.toJSONString(message));
//    }
//}
