package com.micro.controller;

import com.micro.RedisTest.cache.TestCache1;
import com.micro.entity.Test1;
import com.micro.order.pojo.Orders;
import com.micro.utils.RedisTemplateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestRedisController {

    @Autowired
    RedisTemplateUtils redisTemplateUtils;

    @Autowired
    TestCache1 testCache1;

    @GetMapping("/t1")
    public String sets(Test1 test1){
        test1 = new Test1("1","xc");
        redisTemplateUtils.setObject("code",test1,1000L);
        return "success";
    }

    @GetMapping("/t2")
    public String set2(String key){
        Test1 test1 = (Test1)redisTemplateUtils.getObject(key);
        System.out.println(test1.toString());
        return "success";
    }

    @GetMapping("/getOrders")
    @Cacheable(cacheNames = "order",key = "1")
    public List<Orders> getListOrders(String code){
//        Orders orders = testCache1.get(code);
        List<Orders> orders1 = testCache1.getListOrder();
        return orders1;
    }

    @GetMapping("/insert1")
    public String insert1(){
        String insert = testCache1.insert();
        return insert;
    }
}
