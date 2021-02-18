package com.hyh.dang;

import cn.hutool.extra.spring.SpringUtil;
import com.hyh.dang.service.JiJinService;
import com.hyh.dang.util.ContextUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.Resource;

@SpringBootApplication
@EnableSwagger2
@MapperScan("com.hyh.dang.dao")
public class DangApplication {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(DangApplication.class, args);
    }

}
