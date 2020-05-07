package com.micro.springBoot_RabbitMq.OrderTest;

import com.google.gson.Gson;
import com.micro.order.dao.OrderMapper;
import com.micro.order.pojo.Orders;
import com.micro.springBoot_RabbitMq.config.DLXConfig;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

/*
*   实现生成订单异步处理消息
* */
@Service
public class OrderTest {

    @Resource
    OrderMapper orderMapper;

    @Autowired
    RabbitTemplate rabbitTemplate;

    public String send_order_msg(){
        UUID uuid = UUID.randomUUID();
        Orders orders = new Orders();
        orders.setCode(uuid.toString());
        sendMsg(new Gson().toJson(orders));
        return uuid.toString();
    }

    @Async
    public void sendMsg(String order){
        rabbitTemplate.convertAndSend(DLXConfig.TT_DLX_EXCHANGE_01, DLXConfig.TT_DLX_ROUTING_KEY_01, order, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setExpiration("10000");
                return message;
            }
        });
    }

    public String getOrder(String orderId) {
        Orders orders = orderMapper.selectById(orderId);
        if(orders == null){
            return "消息正在处理";
        }
        return orders.toString();
    }
}
