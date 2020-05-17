package com.micro.springBoot_RabbitMq.topic.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "SB_TOPIC_01")
public class TopicConsumer_01 {

    @RabbitHandler
    public void process(String msg){
        System.out.println("TopicConsumer_01..."+msg);
    }

}
