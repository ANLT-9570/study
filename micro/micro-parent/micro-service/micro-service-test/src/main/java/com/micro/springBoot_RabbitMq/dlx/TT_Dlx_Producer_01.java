package com.micro.springBoot_RabbitMq.dlx;

import com.micro.springBoot_RabbitMq.config.DLXConfig;
import com.micro.springBoot_RabbitMq.config.TopicConfig;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TT_Dlx_Producer_01 {

    @Autowired
    RabbitTemplate rabbitTemplate;

    public void send(String routingKey){
        Date date = new Date();
        String msg = "生产..."+date.getTime();
        rabbitTemplate.convertAndSend(DLXConfig.TT_DLX_EXCHANGE_01, DLXConfig.TT_DLX_ROUTING_KEY_01, msg, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setExpiration("10000");//设置消息的过期时间
                return message;
            }
        });
    }

}
