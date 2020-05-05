package com.micro.springBoot_RabbitMq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class FanoutConfig {

    //定义队列名称
    private static final String QUEUE_NAME_FANOUT_01 = "SB_FANOUT_01";
    private static final String QUEUE_NAME_FANOUT_02 = "SB_FANOUT_02";
    //定义交换机名称
    private static final String EXCHANGE_NAME_01 = "SB_EX_01";

    //1 定义队列
    @Bean
    public Queue Fanout_01(){
        return new Queue(QUEUE_NAME_FANOUT_01);
    }
    @Bean
    public Queue Fanout_02(){
        return new Queue(QUEUE_NAME_FANOUT_02);
    }

    //2定义交换机
    @Bean
    public FanoutExchange ExFanoutExchange(){
        return new FanoutExchange(EXCHANGE_NAME_01);
    }

    //3.队列和交互机绑定
    @Bean
    public Binding QueueToExchange_01(Queue Fanout_01,FanoutExchange ExFanoutExchange){
        return BindingBuilder.bind(Fanout_01).to(ExFanoutExchange);
    }
    @Bean
    public Binding QueueToExchange_02(Queue Fanout_02,FanoutExchange ExFanoutExchange){
        return BindingBuilder.bind(Fanout_02).to(ExFanoutExchange);
    }

}
