package com.hyh.dang.config;

import com.hyh.dang.filter.FirstFilter;
import com.hyh.dang.filter.SecondFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

@Configuration
public class FilterOrder {
    @Bean
    public Filter GetFirstFilter(){
        return new FirstFilter();
    }

    @Bean
    public Filter GetSecondFilter(){
        return new SecondFilter();
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean1(){
        FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean();
        filterRegistrationBean.setFilter(GetFirstFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setOrder(1);//order的数值越小 则优先级越高
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean2(){
        FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean();
        filterRegistrationBean.setFilter(GetSecondFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setOrder(2);//order的数值越小 则优先级越高
        return filterRegistrationBean;
    }
}
