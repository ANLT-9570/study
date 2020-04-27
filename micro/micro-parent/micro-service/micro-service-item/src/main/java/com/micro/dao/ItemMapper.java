package com.micro.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.micro.item.pojo.Item;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ItemMapper extends BaseMapper<Item> {
}
