package com.micro.springBoot_RabbitMq.Direct.consumer;

import com.google.gson.Gson;
import com.micro.springBoot_RabbitMq.config.DirectConfig;
import com.micro.springBoot_RabbitMq.topic.Person66;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
@RabbitListener(queues = DirectConfig.QUEUE_NAME_DIRECT_01)    //要监听的队列
public class DirectConsumer_01{

    @RabbitHandler
    public void process(String msg, @Headers Map<String, Object> headers, Channel channel) throws IOException {
        Gson gson = new Gson();
        Person66 person66 = gson.fromJson(msg, Person66.class);
        System.out.println(person66+"DirectConsumer_01..."+msg.toString());
//        int i =1/0;

        //手动ack
        Long deliveryTag  =(Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        //手动签收
        channel.basicAck(deliveryTag,false);
    }

}
