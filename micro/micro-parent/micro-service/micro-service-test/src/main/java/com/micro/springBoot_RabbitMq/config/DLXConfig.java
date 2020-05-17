package com.micro.springBoot_RabbitMq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;


/*
*       死信队列
* */

@Configuration
public class DLXConfig {

    //定义队列名称
    public static final String TT_DLX_QUEUE_01 = "TT_DLX_QUEUE_01";
    public static final String DLX_QUEUE_01 = "DLX_QUEUE_01";
    //定义交换机名称
    public static final String TT_DLX_EXCHANGE_01 = "TT_DLX_EXCHANGE_01";
    public static final String DLX_EXCHANGE_01 = "DLX_EXCHANGE_01";
    // 路由键
    public static final String TT_DLX_ROUTING_KEY_01 = "TT_DLX_ROUTING_KEY_01";
    public static final String DLX_ROUTING_KEY_01 = "#";
    //1 定义队列
    @Bean
    public Queue TT_DLX_01(){
        //绑定一个死信队列
        Map<String,Object> arguments = new HashMap<>();
        arguments.put("x-dead-letter-exchange",DLX_EXCHANGE_01);
//        arguments.put("x-message-ttl", 10000); // 10 秒钟后成为死信
        arguments.put("x-dead-letter-routing-key",DLX_ROUTING_KEY_01);
        return new Queue(TT_DLX_QUEUE_01,true,false,false,arguments);
    }
    @Bean
    public Queue DLX_02(){
        return new Queue(DLX_QUEUE_01);//为true队列持久化
    }

    //2定义交换机
    @Bean
    public DirectExchange TT_DLX_Exchange_01(){
                                    //第一个交互机   ，第二个是否持久化    第三个是否自动删除
        return new DirectExchange(TT_DLX_EXCHANGE_01,true,false);
    }
    @Bean
    public DirectExchange DLX_Exchange_02(){
        return new DirectExchange(DLX_EXCHANGE_01,true,false);
    }


    //3.队列和交互机绑定
    @Bean   // 队列和 交换机绑定
    public Binding QueueToTT_DLXExchange_01(){
        return BindingBuilder.bind(TT_DLX_01()).to(TT_DLX_Exchange_01()).with(TT_DLX_ROUTING_KEY_01);
    }
    @Bean   //2 队列和 2交换机绑定
    public Binding QueueTo_DLX_Exchange_02(){
        return BindingBuilder.bind(DLX_02()).to(DLX_Exchange_02()).with(DLX_ROUTING_KEY_01);
    }


}
