//package com.hyh.dang.filter;
//
//import org.springframework.stereotype.Component;
//
//import javax.servlet.*;
//import java.io.IOException;
//
//@Component
//public class SecondFilter implements Filter {
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        System.out.println("开始 SecondFilter");
//        chain.doFilter(request,response);
//        System.out.println("结束 SecondFilter");
//    }
//}
