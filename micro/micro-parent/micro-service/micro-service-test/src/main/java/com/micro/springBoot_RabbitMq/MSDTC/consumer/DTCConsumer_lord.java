package com.micro.springBoot_RabbitMq.MSDTC.consumer;

import com.micro.common.utils.Result;
import com.micro.item.feign.ItemFeign;
import com.micro.item.pojo.Item;
import com.micro.springBoot_RabbitMq.config.DTCConfig;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;

@Component
@Slf4j
public class DTCConsumer_lord {

    @Resource
    ItemFeign itemFeign;

    @RabbitListener(queues = DTCConfig.DTC_QUEUE_01)
    public void process(Message message, Channel channel) throws IOException {
        log.info("消费者队列...");
        String orderId = message.getMessageProperties().getMessageId();
        if(StringUtils.isEmpty(orderId)){
            log.info("编号为空.......");
            return;
        }
        Result r2 = itemFeign.findByName(orderId);
        if(!StringUtils.isEmpty(r2.getCode())){
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
            return;
        }
        Item item = new Item();
        item.setName(orderId);
        Result result = itemFeign.insert(item);
        if("10000".equals(result.getCode())){
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
            return;
        }
    }
}
