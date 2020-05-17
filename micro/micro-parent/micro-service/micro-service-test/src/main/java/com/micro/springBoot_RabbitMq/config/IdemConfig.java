package com.micro.springBoot_RabbitMq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;


@Configuration
public class IdemConfig {

    //定义队列名称
    public static final String IDEM_QUEUE_01 = "IDEM_QUEUE_01";
    public static final String IDEM_QUEUE_02 = "IDEM_QUEUE_02";
    //定义交换机名称
    public static final String IDEM_EXCHANGE_01 = "IDEM_EXCHANGE_01";
    public static final String IDEM_EXCHANGE_02 = "IDEM_EXCHANGE_02";
    // 路由键
    public static final String IDEM_ROUTING_KEY_01 = "IDEM_ROUTING_KEY_01";
    public static final String IDEM_ROUTING_KEY_02 = "IDEM_ROUTING_KEY_02";

    @Bean
    public Queue queue1(){
        //绑定一个死信队列
        Map<String,Object> arguments=new HashMap<>();
        arguments.put("x-dead-letter-exchange",IDEM_EXCHANGE_02);
        arguments.put("x-dead-letter-routing-key",IDEM_ROUTING_KEY_02);
        return new Queue(IDEM_QUEUE_01,true,false,false,arguments);
    }
    @Bean
    public Queue queue2(){
        return new Queue(IDEM_QUEUE_02);
    }

    @Bean
    public TopicExchange exchange1(){

        return new TopicExchange(IDEM_EXCHANGE_01);
    }
    @Bean
    public TopicExchange exchange2(){
        return new TopicExchange(IDEM_EXCHANGE_02);
    }

    @Bean
    public Binding btb(){
        return BindingBuilder.bind(queue1()).to(exchange1()).with(IDEM_ROUTING_KEY_01);
    }
    @Bean
    public Binding btb2(){
        return BindingBuilder.bind(queue2()).to(exchange2()).with(IDEM_ROUTING_KEY_02);
    }
}
