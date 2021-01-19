package com.hyh.dang.aop;


import lombok.val;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;


@Aspect
@Configuration
public class ControllerAspect {


    @Pointcut("execution (* com.hyh..*Controller.*(..))")
    public void anyhisunControllerMethod() {
    }


    @Around("anyhisunControllerMethod()")
    public Object doAroundController(ProceedingJoinPoint pjp) throws Throwable {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        System.out.println(request.getRequestURL());
        Object[] args = pjp.getArgs();
        for (Object o : args) {
            System.out.println("入参为:" + o);
        }
        Object res = pjp.proceed(args);
        System.out.println("出参为为:" + res);
        return res;
    }
}
