package com.micro.springBoot_RabbitMq.Direct.Producer;

import com.micro.springBoot_RabbitMq.config.DirectConfig;
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
        amqpTemplate.convertAndSend(DirectConfig.EXCHANGE_NAME_02,DirectConfig.ROUTING_KEY_01,msg);

    }

    public void send2(String routingKey){
        Date date = new Date();
        String msg = "生产..."+date.getTime();
        amqpTemplate.convertAndSend("SB_DIRECT_01",msg);

    }

}
