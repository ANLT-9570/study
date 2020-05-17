package com.micro.order.controller;

import com.micro.common.utils.Result;
import com.micro.order.pojo.Orders;
import com.micro.order.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Api(tags = "订单")
@RequestMapping("/v1/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    @PostMapping("/insert")
    @ApiOperation(value = "生成订单",notes = "insert")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "order",value = "订单信息"),
    })
    public Result insert(Orders order){
        return orderService.insert(order);
    }

    @GetMapping("/findById")
    @ApiOperation(value = "根据id查询",notes = "findById")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "订单Id"),
    })
    public Result findById(Long id){
        return orderService.findById(id);
    }

    @GetMapping("/")
    public Result index(Long id){
        return orderService.findById(id);
    }

    @GetMapping("/orderAndItem")
    public Result orderAndItem(Long t){
        return orderService.orderAndItem(t);
    }
}
