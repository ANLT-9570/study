package com.micro.order.anonation.aop;

import com.google.common.util.concurrent.RateLimiter;
import com.micro.order.anonation.ExtRateLimiter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Component
@Aspect
public class RateLimiterAop {

    private Map<String, RateLimiter> rateLimiterMap = new ConcurrentHashMap<>();

    //定义切入点拦截
    @Pointcut("execution(public * com.micro.order.controller.*.*(..))")
    public void rolAop(){

    }

    //使用环绕通知拦截
    @Around("rolAop()")
    public Object doBefore(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Method method = getSignatureMethod(proceedingJoinPoint);
        if(method == null){
            return null;
        }
        //获取参数
        ExtRateLimiter declaredAnnotation = method.getDeclaredAnnotation(ExtRateLimiter.class);
        if(declaredAnnotation == null){
            //直接进入实际请求方法
            return proceedingJoinPoint.proceed();
        }
        double permitsPerSecond = declaredAnnotation.permitsPerSecond();
        long timeOut = declaredAnnotation.timeOut();

        String requestURL = getRequestURL();
        RateLimiter rateLimiter = null;

        if (rateLimiterMap.containsKey(requestURL)){
            rateLimiter = rateLimiterMap.get(requestURL);
        }else {
            rateLimiter = RateLimiter.create(permitsPerSecond);
            rateLimiterMap.put(requestURL,rateLimiter);
        }

        //获取桶中的令牌
        boolean tryAcquire = rateLimiter.tryAcquire(timeOut, TimeUnit.MILLISECONDS);
        if(!tryAcquire){
            //降级
            fallback();
            return null;
        }
        return proceedingJoinPoint.proceed();
    }
    public void fallback(){
        System.out.println("......error......降级..");
        ServletRequestAttributes requestAttributes =(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = requestAttributes.getResponse();
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.println("....rerererer...");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            writer.close();
        }
    }
    public String getRequestURL(){
        return getRequest().getRequestURI();
    }

    public HttpServletRequest getRequest(){
        ServletRequestAttributes requestAttributes =(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return requestAttributes.getRequest();
    }
    //获取方法
    public Method getSignatureMethod(ProceedingJoinPoint proceedingJoinPoint){
        //获取方法签名
        MethodSignature methodSignature =(MethodSignature) proceedingJoinPoint.getSignature();
        Method method = methodSignature.getMethod();
        return method;
    }

}
