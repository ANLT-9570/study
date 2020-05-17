package com.micro.order.service;

import com.micro.common.utils.Result;
import com.micro.order.pojo.Orders;

public interface OrderService {
    Result insert(Orders order);

    Result findById(Long id);

    Result orderAndItem(Long t);
}
