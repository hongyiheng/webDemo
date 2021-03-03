package com.hyh.dang.controller;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONObject;
import com.hyh.dang.anno.RateLimit;
import com.hyh.dang.batch.ConsoleJobTest;
import com.hyh.dang.config.MailProperties;
import com.hyh.dang.dao.ConsulInfoMapper;
import com.hyh.dang.entity.ConsulInfo;
import com.hyh.dang.entity.ConsulInfoExample;
import com.hyh.dang.lua.RedisTest;

import com.hyh.dang.rabbitmqDemo.IRabbitMqService;
import com.hyh.dang.rabbitmqDemo.config.RabbitMqExchange;
import com.hyh.dang.rabbitmqDemo.config.RabbitMqRouting;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@Api(value = "测试 Controller")
public class TestController {
    private final Logger logger = LoggerFactory.getLogger(TestController.class);

    @Value(value = "${gateway}")
    private String port;

    @Resource
    private RedisTest redisTest;

    @Resource
    private ConsulInfoMapper consulInfoMapper;

    @Resource
    private ConsoleJobTest consoleJobTest;

    @Resource
    private IRabbitMqService rabbitMqService;

//    @Autowired
//    private KafkaTemplate<Object, Object> template;

    @PostMapping(value = "/StringMethod")
    @ApiOperation(value="StringMethod")
    public String StringMethod(@RequestBody String str) {
        return str;
    }

    @GetMapping(value = "/redisLua")
    public String redisLua(){
        redisTest.contextLoads();
        return "success";
    }

    @PostMapping(value = "/getConsulInfo")
    @ApiOperation(value="getConsulInfo")
    public ConsulInfo getConsulInfo(@RequestBody ConsulInfo str) {
        return str;
    }

    @GetMapping(value = "/helloBatch")
    @ApiOperation(value="helloBatch")
    public String hello() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        consoleJobTest.testConsoleJob2();
        return "hello";
    }


    @PostMapping(value = "/getList")
    @ApiOperation(value="getList")
    public List<ConsulInfo> getConsulInfoList(@RequestBody ConsulInfo str) {
        ConsulInfoExample consulInfoExample = new ConsulInfoExample();
        ConsulInfoExample.Criteria criteria = consulInfoExample.createCriteria();
        criteria.andAddressEqualTo(str.getAddress());
        return consulInfoMapper.selectByExample(consulInfoExample);
    }

    @GetMapping(value = "/mqDelayTest")
    @ApiOperation(value="mqDelayTest")
    public void mqDelayTest() throws Exception {
        rabbitMqService.send(RabbitMqExchange.MQ_EXCHANGE_TEST, RabbitMqRouting.MQ_ROUTING_TEST,"测试发送消息",30*1000);
    }




//    @GetMapping("/send/{input}")
//    public void sendFoo(@PathVariable String input) {
//        this.template.send("hyh", input);
//    }
//
//    @KafkaListener(id = "webGroup", topics = "hyh")
//    public void listen(String input) {
//        logger.info("input value: {}" , input);
//    }

}
