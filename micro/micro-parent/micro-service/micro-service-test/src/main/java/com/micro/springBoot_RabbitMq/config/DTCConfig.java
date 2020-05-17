package com.micro.springBoot_RabbitMq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DTCConfig {

    //定义队列名称
    public static final String DTC_QUEUE_01 = "DTC_QUEUE_01";
    public static final String DTC_QUEUE_02 = "DTC_QUEUE_02";
    //定义交换机名称
    public static final String DTC_EXCHANGE_01 = "DTC_EXCHANGE_01";
    public static final String DTC_EXCHANGE_02 = "DTC_EXCHANGE_02";
    // 路由键
    public static final String DTC_ROUTING_KEY_01 = "DTC_ROUTING_KEY_01";
    public static final String DTC_ROUTING_KEY_02 = "DTC_ROUTING_KEY_02";


    @Bean
    public Queue queue11(){
        return new Queue(DTC_QUEUE_01);
    }
    @Bean
    public Queue queue22(){
        return new Queue(DTC_QUEUE_02);
    }

    @Bean
    public DirectExchange exchange11(){
        return new DirectExchange(DTC_EXCHANGE_01);
    }
    @Bean
    public DirectExchange exchange22(){
        return new DirectExchange(DTC_EXCHANGE_02);
    }

    @Bean
    public Binding btb(){
        return BindingBuilder.bind(queue11()).to(exchange11()).with(DTC_ROUTING_KEY_01);
    }
    @Bean
    public Binding btb1(){
        return BindingBuilder.bind(queue22()).to(exchange11()).with(DTC_ROUTING_KEY_01);
    }
    @Bean
    public Binding btb2(){
        return BindingBuilder.bind(queue22()).to(exchange22()).with(DTC_ROUTING_KEY_02);
    }
}
