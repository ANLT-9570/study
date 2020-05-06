package com.micro.controller;

import com.micro.springBoot_RabbitMq.Direct.Producer.DirectProducer_01;
import com.micro.springBoot_RabbitMq.Fanout.Producer.FanoutProducer_01;
import com.micro.springBoot_RabbitMq.dlx.TT_Dlx_Producer_01;
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
}
