package com.micro.springBoot_RabbitMq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;


@Configuration
public class OrderDLXConfig {
    //队列
    public static final String ORDER_QUEUE = "ORDER_QUEUE";
    public static final String ORDER_QUEUE_DLX = "ORDER_QUEUE_DLX";

    //交互机
    public static final String ORDER_EXCHANGE = "ORDER_EXCHANGE";
    public static final String ORDER_EXCHANGE_DLX = "ORDER_EXCHANGE_DLX";

    //路由键
    public static final String ORDER_ROUTING_KEY = "ORDER_ROUTING_KEY";
    public static final String ORDER_ROUTING_KEY_DLX = "ORDER_ROUTING_KEY_DLX";

    @Bean
    public Queue OrderQueue(){
        //绑定一个死信队列
        Map<String,Object> arguments = new HashMap<>();
        arguments.put("x-dead-letter-exchange",ORDER_EXCHANGE_DLX);
        arguments.put("x-dead-letter-routing-key",ORDER_ROUTING_KEY_DLX);
        return new Queue(ORDER_QUEUE,true,false,false,arguments);
    }
    @Bean
    public Queue OrderQueueDLX(){
        return new Queue(ORDER_QUEUE_DLX,true,false,false,new HashMap<>());
    }
    @Bean
    public DirectExchange OrderExchange(){
        return new DirectExchange(ORDER_EXCHANGE);
    }
    @Bean
    public DirectExchange OrderExchangeDLX(){
        return new DirectExchange(ORDER_EXCHANGE_DLX);
    }
    @Bean
    public Binding OrderBinding_01(){
        return BindingBuilder.bind(OrderQueue()).to(OrderExchange()).with(ORDER_ROUTING_KEY);
    }
    @Bean
    public Binding OrderBindingDLX_01(){
        return BindingBuilder.bind(OrderQueueDLX()).to(OrderExchangeDLX()).with(ORDER_ROUTING_KEY_DLX);
    }
}
