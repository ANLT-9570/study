package com.micro.springBoot_RabbitMq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class TopicConfig {

    //定义队列名称
    public static final String QUEUE_NAME_TOPIC_01 = "SB_TOPIC_01";
    public static final String QUEUE_NAME_TOPIC_02 = "SB_TOPIC_02";
    public static final String QUEUE_NAME_TOPIC_03 = "TH_TOPIC_03";

    //定义交换机名称
    public static final String EXCHANGE_NAME_02 = "test_topic";
    public static final String EXCHANGE_NAME_03 = "test_topic_exchange";
    public static final String EXCHANGE_NAME_04 = "test_topic_exchange_*";

    // 路由键
    public static final String ROUTING_KEY_01 = "topic.routing.key.01";
    public static final String ROUTING_KEY_02 = "topic.routing.key.02";
    public static final String ROUTING_KEY_03 = "topic.#";

    //1 定义队列
    @Bean
    public Queue Topic_01(){
        return new Queue(QUEUE_NAME_TOPIC_01);
    }
    @Bean
    public Queue Topic_02(){
        return new Queue(QUEUE_NAME_TOPIC_02,true);//为true队列持久化
    }
    @Bean
    public Queue Topic_03(){
        return new Queue(QUEUE_NAME_TOPIC_03,true);//为true队列持久化
    }

    //2定义交换机
    @Bean
    public TopicExchange ExTopicExchange_01(){
        return new TopicExchange(EXCHANGE_NAME_02);
    }
    @Bean
    public TopicExchange ExTopicExchange_02(){
        return new TopicExchange(EXCHANGE_NAME_03);
    }

    @Bean
    public TopicExchange ExTopicExchange_04(){
        return new TopicExchange(EXCHANGE_NAME_04);
    }


    @Bean   //1 队列和 2交换机绑定
    public Binding QueueToTopicExchange_04(){
        return BindingBuilder.bind(Topic_01()).to(ExTopicExchange_01()).with(ROUTING_KEY_01);
    }
    @Bean   //2 队列和 2交换机绑定
    public Binding QueueToTopicExchange_03(){
        return BindingBuilder.bind(Topic_02()).to(ExTopicExchange_01()).with(ROUTING_KEY_02);
    }

    @Bean   //1 队列和 3交换机绑定
    public Binding QueueToTopicExchange_05(){
        return BindingBuilder.bind(Topic_01()).to(ExTopicExchange_02()).with(ROUTING_KEY_01);
    }
    @Bean   //2 队列和 3交换机绑定
    public Binding QueueToTopicExchange_06(){
        return BindingBuilder.bind(Topic_02()).to(ExTopicExchange_02()).with(ROUTING_KEY_02);
    }
    @Bean   //3 队列和 1交换机绑定
    public Binding QueueToTopicExchange_08(){
        return BindingBuilder.bind(Topic_03()).to(ExTopicExchange_01()).with(ROUTING_KEY_03);
    }
    @Bean   //3 队列和 4交换机绑定
    public Binding QueueToTopicExchange_07(){
        return BindingBuilder.bind(Topic_03()).to(ExTopicExchange_04()).with(ROUTING_KEY_03);
    }
}
