package com.micro.order.anonation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExtRateLimiter {

    double permitsPerSecond();//每秒生产的令牌数量
    long timeOut();//获取令牌的超时时间否则走降级
}
