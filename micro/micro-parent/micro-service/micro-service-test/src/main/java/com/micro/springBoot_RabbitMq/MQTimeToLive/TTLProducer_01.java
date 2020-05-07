package com.micro.springBoot_RabbitMq.MQTimeToLive;


import com.micro.order.dao.OrderMapper;
import com.micro.order.pojo.Orders;
import com.micro.springBoot_RabbitMq.config.OrderDLXConfig;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.UUID;

@Component
public class TTLProducer_01 {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Resource
    OrderMapper orderMapper;

    public void send(){
        String msg = UUID.randomUUID().toString();
        Orders orders = new Orders();
        orders.setCode(msg);
        int insert = orderMapper.insert(orders);
        if(insert<0){
            System.out.println("添加失败.....");
            return;
        }
        rabbitTemplate.convertAndSend(OrderDLXConfig.ORDER_EXCHANGE, OrderDLXConfig.ORDER_ROUTING_KEY, msg, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setExpiration("10000");
                return message;
            }
        });
    }

}
