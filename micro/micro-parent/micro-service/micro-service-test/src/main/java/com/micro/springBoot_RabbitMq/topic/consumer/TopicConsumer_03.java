package com.micro.springBoot_RabbitMq.topic.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "TH_TOPIC_03")
public class TopicConsumer_03 {

    @RabbitHandler
    public void process(String msg){
        System.out.println("TopicConsumer_03...656-*-*-"+msg);
    }

}
