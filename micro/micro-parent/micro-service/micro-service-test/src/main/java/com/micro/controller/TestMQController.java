package com.micro.controller;

import com.micro.RedisTest.RedisOrderExpire;
import com.micro.springBoot_RabbitMq.Direct.Producer.DirectProducer_01;
import com.micro.springBoot_RabbitMq.Fanout.Producer.FanoutProducer_01;
import com.micro.springBoot_RabbitMq.MQTimeToLive.TTLProducer_01;
import com.micro.springBoot_RabbitMq.MSDTC.DTCProducer;
import com.micro.springBoot_RabbitMq.OrderTest.OrderTest;
import com.micro.springBoot_RabbitMq.dlx.TT_Dlx_Producer_01;
import com.micro.springBoot_RabbitMq.idempotence.IdempotenceProduce;
import com.micro.springBoot_RabbitMq.topic.Producer.TopicProducer_01;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TestMQController {

    @Resource
    FanoutProducer_01 fanoutProducer_01;
    @Autowired
    DirectProducer_01 producer_01;
    @Autowired
    TopicProducer_01 topicProducer_01;
    @Autowired
    TT_Dlx_Producer_01 tt_dlx_producer_01;
    @Autowired
    OrderTest orderTest;
    @Autowired
    RedisOrderExpire orderExpire;
    @Autowired
    TTLProducer_01 ttlProducer_01;
    @Autowired
    IdempotenceProduce idempotenceProduce;
    @Autowired
    DTCProducer dtcProducer;

    @GetMapping("send_fanout01")
    public void send(String queueName){
        fanoutProducer_01.send(queueName);
    }

    @GetMapping("send_direct01")
    public void direct_send(String routingKey){
        producer_01.send(routingKey);
    }
    @GetMapping("send_direct02")
    public void direct_send2(String routingKey){
        producer_01.send2(routingKey);
    }

    @GetMapping("send_direct03")
    public void direct_send3(String routingKey){
        producer_01.send3(routingKey);
    }

    @GetMapping("send_topic03")
    public void send_topic03(String routingKey){
        topicProducer_01.send(routingKey);
    }

    @GetMapping("send_td")
    public void tt_dlx_producer_01(String routingKey){
        tt_dlx_producer_01.send(routingKey);
    }


    @GetMapping("send_order_msg")
    public String send_order_msg(){
        return orderTest.send_order_msg();
    }

    //主动调用接口查询消费结果
    @GetMapping("/getOrder")
    public String send_order_msg(String orderId){
        return orderTest.getOrder(orderId);
    }

    //30分钟未支付，自动过期 基于redis
    @GetMapping("/isExpire")
    public String redisTimeToLive(){
        return orderExpire.RedisOrder();
    }

    //30分钟未支付，自动过期 基于MQ
    @GetMapping("/MQisExpire")
    public String MQTimeToLive(){
        ttlProducer_01.send();
        return "success";
    }

    //幂等性解决
    @GetMapping("/Idem")
    public String Idem(){
        idempotenceProduce.IP();
        return "success";
    }

    //分布式事务解决
    @GetMapping("/det")
    public String det(){
        dtcProducer.process();
        return "success";
    }
}
