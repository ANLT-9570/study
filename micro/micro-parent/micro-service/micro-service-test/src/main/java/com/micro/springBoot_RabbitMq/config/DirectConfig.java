package com.micro.springBoot_RabbitMq.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DirectConfig {

    //定义队列名称
    public static final String QUEUE_NAME_DIRECT_01 = "SB_DIRECT_01";
    public static final String QUEUE_NAME_DIRECT_02 = "SB_DIRECT_02";
    //定义交换机名称
    public static final String EXCHANGE_NAME_02 = "SB_EX_02";
    public static final String EXCHANGE_NAME_03 = "SB_EX_03";
    // 路由键
    public static final String ROUTING_KEY_01 = "routing_key_01";
    public static final String ROUTING_KEY_02 = "routing_key_02";
    //1 定义队列
    @Bean
    public Queue Direct_01(){
        return new Queue(QUEUE_NAME_DIRECT_01,true);
    }
    @Bean
    public Queue Direct_02(){
        return new Queue(QUEUE_NAME_DIRECT_02,true);//为true队列持久化
    }

    //2定义交换机
    @Bean
    public DirectExchange ExDirectExchange_01(){
                                    //第一个交互机   ，第二个是否持久化    第三个是否自动删除
        return new DirectExchange(EXCHANGE_NAME_02,true,false);
    }
    @Bean
    public DirectExchange ExDirectExchange_02(){
        return new DirectExchange(EXCHANGE_NAME_03,true,false);
    }


    //3.队列和交互机绑定
    @Bean   //1 队列和 2交换机绑定
    public Binding QueueToDirectExchange_01(){
        return BindingBuilder.bind(Direct_01()).to(ExDirectExchange_02()).with(ROUTING_KEY_01);
    }
    @Bean   //2 队列和 2交换机绑定
    public Binding QueueToDirectExchange_02(){
        return BindingBuilder.bind(Direct_02()).to(ExDirectExchange_02()).with(ROUTING_KEY_02);
    }

    @Bean   //2 队列和 1交换机绑定
    public Binding QueueToDirectExchange_03(){
        return BindingBuilder.bind(Direct_02()).to(ExDirectExchange_01()).with(ROUTING_KEY_02);
    }

//    @Bean
//    public SimpleMessageListenerContainer messageListenerContainer(){
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//        container
//        return container;
//    }
}
