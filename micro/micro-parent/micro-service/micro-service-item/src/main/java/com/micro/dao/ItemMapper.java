package com.micro.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.micro.item.pojo.Item;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ItemMapper extends BaseMapper<Item> {
    @Select(value = "select * from m_item where name = #{name}")
    Item selectByName(@Param("name") String name);
}
