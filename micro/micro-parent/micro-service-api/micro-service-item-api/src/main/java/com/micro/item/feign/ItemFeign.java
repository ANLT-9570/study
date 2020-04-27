package com.micro.item.feign;

import com.micro.common.utils.Result;
import com.micro.item.pojo.Item;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@FeignClient(name = "app-items",fallback = ItemServiceFallback.class)//,fallback = ItemServiceFallback.class
public interface ItemFeign {

    @PostMapping("/v1/item/insert")
    public Result insert(Item item);

    @GetMapping("/findById")
    public Result findById(Long id);
}
