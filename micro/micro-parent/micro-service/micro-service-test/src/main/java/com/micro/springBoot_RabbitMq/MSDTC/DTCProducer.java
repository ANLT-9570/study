package com.micro.springBoot_RabbitMq.MSDTC;

import com.google.gson.Gson;
import com.micro.order.dao.OrderMapper;
import com.micro.order.pojo.Orders;
import com.micro.springBoot_RabbitMq.config.DTCConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.UUID;

@Component
@Slf4j
public class DTCProducer {

    @Autowired
    RabbitTemplate rabbitTemplate;
    @Resource
    OrderMapper orderMapper;
    RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback(){
        @Override
        public void confirm(CorrelationData correlationData, boolean ack, String s) {
            if(!ack){//如果投递失败直接重试投递
                process();
            }
            log.info("消息投递成功:{},s:{}",correlationData.getId(),s);
        }
    };
    @Transactional
    public void process(){
        //开启投递消息后回调
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback(confirmCallback);
        log.info("............");
        String code = UUID.randomUUID().toString();
        Orders orders = new Orders();
        orders.setCode(code);
        int insert = orderMapper.insert(orders);
        if(insert>0){
            CorrelationData correlationData = new CorrelationData();
            correlationData.setId(code);
            rabbitTemplate.convertAndSend(DTCConfig.DTC_EXCHANGE_01, DTCConfig.DTC_ROUTING_KEY_01, new Gson().toJson(orders), new MessagePostProcessor() {
                @Override
                public Message postProcessMessage(Message message) throws AmqpException {
                    message.getMessageProperties().setMessageId(code);
                    return message;
                }
            },correlationData);
        }
    }

}
