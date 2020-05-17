package com.micro.order.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.micro.order.pojo.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface OrderMapper extends BaseMapper<Orders> {

    @Select(value = "select * from orders where code = #{code}")
    Orders selectByCode(@Param("code") String code);
}
