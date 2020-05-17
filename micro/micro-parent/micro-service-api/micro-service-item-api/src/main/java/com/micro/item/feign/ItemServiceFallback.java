package com.micro.item.feign;

import com.micro.common.utils.Result;
import com.micro.item.pojo.Item;
import org.springframework.stereotype.Component;

@Component
public class ItemServiceFallback implements ItemFeign {
    @Override
    public Result insert(Item order) {
        return Result.successResult("您请求的数据正在来的路上，消等.....");
    }

    @Override
    public Result findById(Long id) {
        return Result.successResult("您请求的数据正在来的路上，消等.....");
    }

    @Override
    public Result findByName(String Name)  {
        return Result.successResult("您请求的数据正在来的路上，消等.....");
    }
}
