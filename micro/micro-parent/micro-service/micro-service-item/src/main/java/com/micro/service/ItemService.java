package com.micro.service;

import com.micro.common.utils.Result;
import com.micro.item.pojo.Item;

public interface ItemService {

    Result insert(Item order);

    Result findById(Long id);
}
