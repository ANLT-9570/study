package com.micro.springBoot_RabbitMq.Direct.consumer;

import com.micro.springBoot_RabbitMq.config.DirectConfig;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "SB_DIRECT_02")
public class DirectConsumer_02 {

    @RabbitHandler
    public void process(String msg){
        System.out.println("DirectConsumer_02..."+msg.toString());
    }

}
