package com.micro.order.controller;

import com.google.common.util.concurrent.RateLimiter;
import com.micro.order.anonation.ExtRateLimiter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * 使用Ratelimiter实现令牌桶方式限流
 */
@RestController
public class TestOrderController {

    //方法中传入的参数以每秒为固定单位的速率值 1r/s 每秒中忘桶中放入一个令牌
    RateLimiter rateLimiter = RateLimiter.create(1);//独立线程

    @GetMapping("/xl")
    @ExtRateLimiter(permitsPerSecond = 1.0,timeOut = 500)
    public String addOrder(){
        //从桶中拿到令牌等待的时间
//        double acquire = rateLimiter.acquire();
        //如果在500毫秒内没有获取到令牌，则直接走服务降级处理
        boolean tryAcquire = rateLimiter.tryAcquire(500, TimeUnit.MILLISECONDS);
        if(!tryAcquire){
            System.out.println("抢购失败......");
            return "抢购失败...";
        }
        System.out.println("业务逻辑处理......");
        return "抢购成功...";
    }

}
