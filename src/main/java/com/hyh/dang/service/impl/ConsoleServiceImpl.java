package com.hyh.dang.service.impl;

import com.hyh.dang.service.ConsoleService;
import org.springframework.stereotype.Service;

@Service
public class ConsoleServiceImpl implements ConsoleService {
    @Override
    public String convert2UpperCase(String data) {
        return data.toUpperCase();
    }
}
