package com.xc.controller;

import com.xc.Item;
import com.xc.config.JdbcConfigBean;
import com.xc.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;
    @Value("${server.port}")
    private String port;

//    @Autowired


    @Autowired
    private JdbcConfigBean jdbcConfigBean;
    /**
     * 对外提供接口服务，查询商品信息
     *
     * @param id
     * @return
     */
    @GetMapping(value = "item/{id}")
    public Item queryItemById(@PathVariable("id") Long id) {
        System.out.println("---------端口-------"+port);
        return this.itemService.queryItemById(id);
    }

    @GetMapping(value = "/testConfig")
    public String testConfig() {
        System.out.println("---------端口-------"+port);
        return jdbcConfigBean.toString();
    }

}