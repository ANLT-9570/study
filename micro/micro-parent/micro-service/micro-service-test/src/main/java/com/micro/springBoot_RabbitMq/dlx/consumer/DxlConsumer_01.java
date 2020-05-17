package com.micro.springBoot_RabbitMq.dlx.consumer;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
@RabbitListener(queues = "DLX_QUEUE_01")
public class DxlConsumer_01 {

    @RabbitHandler
    public void process(String msg, @Headers Map<String, Object> headers, Channel channel) throws IOException {
        System.out.println("DLX_QUEUE_01..."+msg);
        Long o = (Long)headers.get(AmqpHeaders.DELIVERY_TAG);
        channel.basicAck(o,false);
    }

}
