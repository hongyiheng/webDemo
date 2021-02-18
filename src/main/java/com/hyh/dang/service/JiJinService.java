package com.hyh.dang.service;

import com.hyh.dang.entity.Jijin;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface JiJinService {
    List<Jijin> findAll();

    int deleteAll();

    void produceExcel() throws InterruptedException;

}
