package com.micro.order.service.impl;

//import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.micro.common.utils.Result;
import com.micro.item.feign.ItemFeign;
import com.micro.item.pojo.Item;
import com.micro.order.dao.OrderMapper;
import com.micro.order.pojo.Orders;
import com.micro.order.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private ItemFeign itemFeign;

    @Override
    public Result insert(Orders order) {
        UUID uuid = UUID.randomUUID();
        order.setCode(uuid.toString());
        int insert = orderMapper.insert(order);
        return Result.successResult();
    }

    @Override
    public Result findById(Long id) {
        Orders o = (Orders)orderMapper.selectById(id);
        return Result.successResult(o);
    }


//    @LcnTransaction
    @Transactional
    public Result orderAndItem(Long t) {
        Orders orders = new Orders();
        orders.setCode(UUID.randomUUID().toString());

        int _orders = orderMapper.insert(orders);

        Item item = new Item();
        item.setName("etl"+UUID.randomUUID().toString());
        Result insert = itemFeign.insert(item);

        Long i = 10/t;

        return Result.successResult();
    }

}
