package com.micro.order.anonation.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class RateLimiterAop {

    //定义切入点拦截
    @Pointcut("execution(public * com.micro.order.controller.*.*(..))")
    public void rolAop(){

    }

    //使用环绕通知拦截
    @Around("rolAop()")
    public Object doBefore(ProceedingJoinPoint proceedingJoinPoint){
        try {
            Object proceed = proceedingJoinPoint.proceed();

        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }

}
