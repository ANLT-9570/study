package com.micro.springBoot_RabbitMq.dlx.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "DLX_QUEUE_01")
public class DxlConsumer_01 {

    @RabbitHandler
    public void process(String msg){
        System.out.println("DLX_QUEUE_01..."+msg);
    }

}
