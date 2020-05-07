package com.micro.springBoot_RabbitMq.MQTimeToLive.consumer;

import com.micro.springBoot_RabbitMq.config.OrderDLXConfig;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
@RabbitListener(queues = OrderDLXConfig.ORDER_QUEUE_DLX)
public class OrderDLXConsumer {

    @RabbitHandler
    public void process(String msg, @Headers Map<String, Object> headers, Channel channel) throws IOException {
        System.out.println("订单过期了..."+msg);
        Long aLong =(Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        channel.basicAck(aLong,false);
    }

}
