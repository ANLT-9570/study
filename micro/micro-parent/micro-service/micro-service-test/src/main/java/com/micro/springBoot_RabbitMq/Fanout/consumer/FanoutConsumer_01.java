package com.micro.springBoot_RabbitMq.Fanout.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "SB_FANOUT_01")
public class FanoutConsumer_01 {

    @RabbitHandler
    public void process(String msg){
        System.out.println("FanoutConsumer_01..."+msg);
    }

}
