package com.micro.controller;

import com.micro.common.utils.Result;
import com.micro.item.pojo.Item;
import com.micro.service.ItemService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/v1/item")
public class ItemController {

    @Resource
    private ItemService itemService;

    @PostMapping("/insert")
    @ApiOperation(value = "生成订单",notes = "insert")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "order",value = "订单信息"),
    })
    public Result insert(Item item){
        return itemService.insert(item);
    }

    @GetMapping("/findById")
    @ApiOperation(value = "根据id查询",notes = "findById")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "订单Id"),
    })
    public Result findById(Long id){
        return itemService.findById(id);
    }

    @GetMapping("/findByName")
    @ApiOperation(value = "根据name查询",notes = "findByName")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Name",value = "name"),
    })
    public Result findByName(String Name){
        return itemService.findByName(Name);
    }
}
