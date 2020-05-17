package com.micro.springBoot_RabbitMq.dlx.consumer;

import com.google.gson.Gson;
import com.micro.order.dao.OrderMapper;
import com.micro.order.pojo.Orders;
import com.micro.springBoot_RabbitMq.config.DLXConfig;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;

@Component
//@RabbitListener(queues = DLXConfig.TT_DLX_QUEUE_01)
public class LordConsumer_02 {

    @Resource
    OrderMapper orderMapper;

//    @RabbitHandler
    public void process(String msg, @Headers Map<String, Object> headers, Channel channel) throws IOException {
        System.out.println("TT_DLX_QUEUE_01..."+msg);
        Orders orders = new Gson().fromJson(msg, Orders.class);
        if(orders == null){
            return;
        }
        orderMapper.insert(orders);
        //手动ack
        Long deliveryTag  =(Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        //手动签收
        channel.basicAck(deliveryTag,false);
    }

}
