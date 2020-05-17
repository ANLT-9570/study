package com.micro.springBoot_RabbitMq.idempotence.consumer;

import com.google.gson.Gson;
import com.micro.order.dao.OrderMapper;
import com.micro.order.pojo.Orders;
import com.micro.springBoot_RabbitMq.config.IdemConfig;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;

/*
* 使用全局id解决幂等性
* */
@Component
@Slf4j
public class IdempotenceConsumer {

    @Resource
    OrderMapper orderMapper;

    @RabbitListener(queues = IdemConfig.IDEM_QUEUE_01)
    public void process(Message message, Channel channel) throws IOException {
        String msg = new String(message.getBody());
        log.info("信息:{}",msg);
        String orderId = message.getMessageProperties().getMessageId();

        Orders dbOrders = orderMapper.selectByCode(orderId);

        if(dbOrders != null){
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
            return;
        }
        Orders orders = new Gson().fromJson(msg, Orders.class);
        int insert = orderMapper.insert(orders);
        if(insert > 0){
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
            return;
        }

    }

}
