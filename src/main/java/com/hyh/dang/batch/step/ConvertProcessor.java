package com.hyh.dang.batch.step;

import com.hyh.dang.service.ConsoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 实现ItemProcessor接口，它有两个泛型，分别是I和O，I是读阶段获取的数据，O是提交给写阶段的数据。
 * 使用ConsoleService服务，对数据进行大写转换，里面的实现直接使用字符串的toUpperCase()方法
 */
@Slf4j
public class ConvertProcessor implements ItemProcessor<String,String> {
    @Autowired
    private ConsoleService consoleService;

    @Override
    public String process(String data) {
        String dataProcessed = consoleService.convert2UpperCase(data);
        log.info(data +" process data --> " + dataProcessed);
        return dataProcessed;
    }
}
