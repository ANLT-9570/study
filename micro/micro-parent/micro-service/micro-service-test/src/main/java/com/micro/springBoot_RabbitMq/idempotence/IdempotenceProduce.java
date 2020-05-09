package com.micro.springBoot_RabbitMq.idempotence;

import com.google.gson.Gson;
import com.micro.order.pojo.Orders;
import com.micro.springBoot_RabbitMq.config.IdemConfig;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/*
* 解决幂等性
* */
@Component
public class IdempotenceProduce {

    @Autowired
    RabbitTemplate rabbitTemplate;

    public void IP(){
        String code = UUID.randomUUID().toString();
        Orders orders = new Orders();
        orders.setCode(code);
        String msg = new Gson().toJson(orders);
        rabbitTemplate.convertAndSend(IdemConfig.IDEM_EXCHANGE_01, IdemConfig.IDEM_ROUTING_KEY_01, msg, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setMessageId(code);
                return message;
            }
        });
    }

}
