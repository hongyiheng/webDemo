package com.hyh.dang.batch;

import com.hyh.dang.batch.after.ConsoleJobEndListener;
import com.hyh.dang.batch.step.ConsoleWriter;
import com.hyh.dang.batch.step.ConvertProcessor;
import com.hyh.dang.batch.step.StringReader;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 添加注解@Configuration及和@EnableBatchProcessing，
 * 标识为配置及启用Spring Batch的配置（可以直接使用JobBuilderFactory及StepBuilderFactory分别用于创建Job和Step）。
 * 创建ItemReader、ItemWriter、ItemProcessor、Listener对应的Bean，以供Step及Job的注入。
 * 使用stepBuilderFactory创建作业Step，其中chunk进行面向块的处理，即多次读取后再写入，提高效率。当前配置是3个为一个chunk。
 * 使用jobBuilderFactory添加step，创建任务。
 * 注意step和Job都需要有对应的名称(get方法确定)，此处直接使用方法名作为Job和Step的名称。
 */
@Configuration
@EnableBatchProcessing
public class ConsoleBatchConfig {
    @Autowired
    public JobBuilderFactory jobBuilderFactory;
    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job consoleJob(Step consoleStep, JobExecutionListener consoleListener){
        String funcName = Thread.currentThread().getStackTrace()[1].getMethodName();
        return jobBuilderFactory.get(funcName).listener(consoleListener).flow(consoleStep)
                .end().build();
    }

    @Bean
    public Step consoleStep(ItemReader stringReader, ItemProcessor convertProcessor
            , ItemWriter consoleWriter, JobExecutionListener consoleListener){
        String funcName = Thread.currentThread().getStackTrace()[1].getMethodName();
        return stepBuilderFactory.get(funcName).listener(consoleListener)
                .<String,String>chunk(3).reader(stringReader).processor(convertProcessor)
                .writer(consoleWriter).build();
    }

    @Bean
    public ItemReader stringReader(){return new StringReader();}

    @Bean
    public ItemWriter consoleWriter(){return new ConsoleWriter();}

    @Bean
    public ItemProcessor convertProcessor(){return new ConvertProcessor();}

    @Bean
    public JobExecutionListener consoleListener(){return new ConsoleJobEndListener();}
}

