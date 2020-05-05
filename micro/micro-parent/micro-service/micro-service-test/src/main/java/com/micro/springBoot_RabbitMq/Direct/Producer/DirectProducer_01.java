package com.micro.springBoot_RabbitMq.Direct.Producer;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DirectProducer_01 {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send(String routingKey){
        Date date = new Date();
        String msg = "生产..."+date.getTime();
        amqpTemplate.convertAndSend(routingKey,msg);

    }

}
