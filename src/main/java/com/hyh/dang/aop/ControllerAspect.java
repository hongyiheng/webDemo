package com.hyh.dang.aop;


import com.google.common.util.concurrent.RateLimiter;
import lombok.val;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;



@Aspect
@Configuration
public class ControllerAspect {
    Logger logger = LoggerFactory.getLogger(ControllerAspect.class);
    private static RateLimiter rateLimiter = RateLimiter.create(50);

    @Pointcut("execution (* com.hyh..*Controller.*(..))")
    public void anyhisunControllerMethod() {
    }

    @Around("anyhisunControllerMethod()")
    public Object doAroundController(ProceedingJoinPoint pjp) throws Throwable {
        Object res = null;
        try {
            //tryAcquire()是非阻塞, rateLimiter.acquire()是阻塞的
            if (rateLimiter.tryAcquire(2, TimeUnit.SECONDS)) {
                RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
                HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
                logger.info(request.getRequestURL().toString());
                Object[] args = pjp.getArgs();
                for (Object o : args) {
                    logger.info("入参为:" + o);
                }
                res = pjp.proceed(args);
                logger.info("出参为为:" + res);
            } else {
                //拒绝了请求（服务降级）
               res = "The system is busy, please visit after a while";
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return res;
    }
}
