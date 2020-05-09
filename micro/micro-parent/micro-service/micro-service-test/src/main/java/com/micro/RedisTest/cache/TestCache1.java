package com.micro.RedisTest.cache;

import com.micro.order.dao.OrderMapper;
import com.micro.order.pojo.Orders;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@Service
public class TestCache1 {

    @Resource
    OrderMapper orderMapper;

    public String insert(){
        String s = UUID.randomUUID().toString();

        Orders orders = new Orders();
        orders.setCode(s);
        orderMapper.insert(orders);
        return s;
    }

    public Orders get(String code) {
        Orders orders = orderMapper.selectByCode(code);
        return orders;
    }

    public List<Orders> getListOrder() {
        System.out.println("进数据库了.......");
        return orderMapper.selectList(null);

    }
}
