package com.micro.springBoot_RabbitMq.Direct.Producer;

import com.google.gson.Gson;
import com.micro.springBoot_RabbitMq.config.DirectConfig;
import com.micro.springBoot_RabbitMq.topic.Person66;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DirectProducer_01{

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    RabbitTemplate rabbitTemplate;

    Logger log = LoggerFactory.getLogger(DirectProducer_01.class);

    //消息发送后回调这个方法
//    如果消息没有到exchange,则confirm回调,ack=false
//    如果消息到达exchange,则confirm回调,ack=true
//    只确认消息是否正确到达 Exchange 中。
    final RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
        @Override
        public void confirm(CorrelationData correlationData, boolean ack, String cause) {
            System.out.println(correlationData);
            if(ack){
                System.out.println("消息发送成功..");
            }else{
                System.out.println("消息发送失败..");
            }
            System.out.println(cause);
        }
    };
    //消息发送到没有的队列或者路由键就会回调这个方法
//    exchange到queue成功,则不回调return
//    exchange到queue失败,则回调return(需设置mandatory=true,否则不回回调,消息就丢了)
//    消息没有正确到达队列时触发回调，如果正确到达队列不执行。
    final RabbitTemplate.ReturnCallback returnCallback = new RabbitTemplate.ReturnCallback() {
        @Override
        public void returnedMessage(Message message, int code, String cause, String Exchange, String routingKey) {
            System.out.println(message);
            log.info("发送的消息:{},响应码:{},原因:{},交换机:{},路由键:{}",message,code,code,Exchange,routingKey);
        }
    };

    public void send(String routingKey){
        Date date = new Date();
        String msg = "生产..."+date.getTime();
        amqpTemplate.convertAndSend(DirectConfig.EXCHANGE_NAME_02,DirectConfig.ROUTING_KEY_01,msg);

    }

    public void send2(String routingKey){
        Date date = new Date();
        String msg = "生产..."+date.getTime();
        //第一个参数队列 第二个参数要发送的消息
        amqpTemplate.convertAndSend("SB_DIRECT_01",msg);

    }

    public void send3(String routingKey){
        System.out.println("生产者推送消息......");
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback(confirmCallback);
        rabbitTemplate.setReturnCallback(returnCallback);
        Person66 person66 = new Person66("xc","24","男");
        Gson gson = new Gson();
        String toJson = gson.toJson(person66);
        CorrelationData correlationData = new CorrelationData(new Date().getTime()+"");
        //第一个参数交换机 第二个参数路由键  第三个参数要发送的消息
        rabbitTemplate.convertAndSend("SB_EX_03",routingKey,toJson,correlationData);

    }


}
