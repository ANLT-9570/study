package com.micro.service.impl;

import com.micro.common.utils.Result;
import com.micro.dao.ItemMapper;
import com.micro.item.pojo.Item;
import com.micro.service.ItemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ItemServiceImpl implements ItemService {

    @Resource
    private ItemMapper itemMapper;

    @Override
    public Result insert(Item order) {
        int insert = itemMapper.insert(order);
        return Result.successResult();
    }

    @Override
    public Result findById(Long id) {
        Item o = (Item)itemMapper.selectById(id);
        return Result.successResult(o);
    }
}
