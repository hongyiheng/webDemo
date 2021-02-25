package com.hyh.dang.batch.step;


import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

/**
 * （1）StringReader实现ItemReader接口；
 * （2）messages是数据源；
 * （3）count表示读取数据的下标，每读一次，下标自增，读取完后返回null表示结束。同时把count置为0，以方便下次读取。
 * （4）日志输出使用的是logback，结合lombok的@Slf4j注解，直接可使用log进行输出，简化操作。
 */
@Slf4j
public class StringReader implements ItemReader<String> {
    private String[] messages = {"aaa1","aaa2","aaa3","aaa4"};
    private int count = 0;
    @Override
    public String read() throws UnexpectedInputException, ParseException, NonTransientResourceException {
        if(count < messages.length){
            String message = messages[count++];
            log.info("read data:"+message);
            return message;
        }else{
            log.info("read data end.");
            count = 0;
        }
        return null;
    }
}

