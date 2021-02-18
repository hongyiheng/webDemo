package com.hyh.dang.service.impl;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.HashUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.hyh.dang.dao.JijinMapper;
import com.hyh.dang.entity.Jijin;
import com.hyh.dang.entity.JijinExample;
import com.hyh.dang.entity.JijinExcelDO;
import com.hyh.dang.service.JiJinService;
import jnr.ffi.annotations.In;
import org.python.util.PythonInterpreter;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class JiJinServiceImpl implements JiJinService {
    @Resource
    private JijinMapper jijinMapper;

    @Resource
    private HttpServletResponse response;

    @Override
    public List<Jijin> findAll() {
        List<Jijin> list = jijinMapper.selectByExample(new JijinExample());
        return list;
    }

    @Override
    public int deleteAll() {
        return jijinMapper.deleteByExample(new JijinExample());
    }

    @Override
    public void produceExcel() throws InterruptedException {

        List<Jijin> list = jijinMapper.selectByExample(new JijinExample());
        CollUtil.reverse(list);

        String name = DateUtil.today() + "基金净值表";
        List<Map<String, Object>> mapList = new ArrayList<>();
        //格式整理
        mapList = checkExcelDate(list);
        ArrayList<Map<String, Object>> rows = CollUtil.newArrayList(mapList);

        ExcelWriter writer = ExcelUtil.getWriter("d:/jijin/" + name + ".xls");
        writer.write(rows, true);
        writer.close();

        //删除全表
        jijinMapper.deleteByExample(new JijinExample());

    }


    public List<Map<String, Object>> checkExcelDate(List<Jijin> list) {
        if (list == null && list.size() <= 0) {
            return null;
        }
        List<Map<String, Object>> res = new ArrayList<>();
        Map<String, Object> map = MapUtil.newHashMap(true);
        String num = list.get(0).getNum();
        Float raiseCount = new Float(0);
        DecimalFormat decimalFormat= new  DecimalFormat( ".00" );
        for (Jijin jijin : list) {
            if (num.equals(jijin.getNum())) {
                map.put("基金号", jijin.getNum());
                map.put("基金名", jijin.getName());
                map.put(jijin.getCreatedt(), jijin.getRaise());
                Float raise = Float.parseFloat(StrUtil.removeSuffix(jijin.getRaise(), "%"));
                raiseCount += raise;
            } else {
                map.put("合计", decimalFormat.format(raiseCount) + "%");
                res.add((Map<String, Object>) ((HashMap<String, Object>) map).clone());
                raiseCount = 0f;
                Float raise = Float.parseFloat(StrUtil.removeSuffix(jijin.getRaise(), "%"));
                raiseCount += raise;
                num = jijin.getNum();
                map.put("基金号", jijin.getNum());
                map.put("基金名", jijin.getName());
                map.put(jijin.getCreatedt(), jijin.getRaise());
            }
        }
        map.put("合计", decimalFormat.format(raiseCount) + "%");
        res.add(map);
        return res;
    }
}
