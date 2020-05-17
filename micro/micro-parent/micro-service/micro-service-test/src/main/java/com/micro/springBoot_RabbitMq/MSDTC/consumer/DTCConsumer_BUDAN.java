package com.micro.springBoot_RabbitMq.MSDTC.consumer;

import com.google.gson.Gson;
import com.micro.common.utils.Result;
import com.micro.item.feign.ItemFeign;
import com.micro.item.pojo.Item;
import com.micro.order.dao.OrderMapper;
import com.micro.order.pojo.Orders;
import com.micro.springBoot_RabbitMq.config.DTCConfig;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
/*
* 补单队列
* */
@Component
@Slf4j
public class DTCConsumer_BUDAN {

    static final ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>();
    static int i=1;
    @Resource
    OrderMapper orderMapper;

    @RabbitListener(queues = DTCConfig.DTC_QUEUE_02)
    public void process(Message message, Channel channel) throws IOException {

        log.info("补单队列.......");
        String orderId = message.getMessageProperties().getMessageId();
        if(StringUtils.isEmpty(orderId)){
            log.info("编号为空.......");
            return;
        }

        Integer integer = threadLocal.get();
        if(integer==null){
            threadLocal.set(i);
        }else{
            log.info("-*****"+integer++);
            threadLocal.set(integer++);
            log.info("-*-*-本线程名:{},本线程值:{}",Thread.currentThread().getName(),threadLocal.get());
        }
        if(integer ==4){
            log.info("写到日志库中....threadLocal....等于{}",integer);
            return;
        }
        Orders orders = orderMapper.selectByCode(orderId);
        if(orders!=null){
           channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
            return;
        }
        String msg = new String (message.getBody());
        Orders orders1 = new Gson().fromJson(msg, Orders.class);
        int insert = orderMapper.insert(orders1);
        if(insert>0){
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
            return;
        }

    }
}
