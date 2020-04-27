package com.micro.order.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.micro.order.pojo.Orders;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper extends BaseMapper<Orders> {

}
