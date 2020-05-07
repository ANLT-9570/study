package com.micro.RedisTest;

import com.micro.common.utils.JedisUtil;
import com.micro.order.dao.OrderMapper;
import com.micro.order.pojo.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.util.UUID;

/*
* 30分钟未支付，自动过期使用redi实现
*
* */
@Service
public class RedisOrderExpire {

    @Resource
    OrderMapper orderMapper;

    public String RedisOrder(){
        String code = UUID.randomUUID().toString();
        Orders orders = new Orders();
        orders.setCode(code);
        orderMapper.insert(orders);
        Jedis jedis = JedisUtil.getInstance().getJedis();
        jedis.set(code,code);
        jedis.expire(code,10);
        return "success";
    }

}
