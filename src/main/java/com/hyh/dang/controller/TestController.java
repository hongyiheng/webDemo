package com.hyh.dang.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Value(value = "${gateway}")
    private String port;

    @PostMapping(value = "/StringMethod")
    public String StringMethod(@RequestBody String str) {
        return str;
    }

}
