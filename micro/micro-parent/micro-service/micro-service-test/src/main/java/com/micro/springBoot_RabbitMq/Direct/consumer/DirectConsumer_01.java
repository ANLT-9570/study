package com.micro.springBoot_RabbitMq.Direct.consumer;

import com.micro.springBoot_RabbitMq.config.DirectConfig;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = DirectConfig.QUEUE_NAME_DIRECT_01)    //要监听的队列
public class DirectConsumer_01 {

    @RabbitHandler
    public void process(String msg){
        System.out.println("DirectConsumer_01..."+msg);
    }

}
