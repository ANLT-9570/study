package com.micro.MQ.producer;

import com.micro.MQ.MQConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/*
* 简单队列生产者
* */
public class Producer_1 {

    //队列名称
    private static final String QUEUE_NAME = "NAME_xc";

    public static void main(String[] args) throws IOException, TimeoutException {
        //创建一个连接
        Connection connection = MQConnectionUtils.newConnection();

        //创建通道
        Channel channel = connection.createChannel();

        //创建一个队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        for(int i=1;i<=100;i++){

        //创建信息
        String msg = "生产者11122"+"_"+i;
        System.out.println("消息投递.....");
        //生产者发送消息
        channel.basicPublish("",QUEUE_NAME,null,msg.getBytes());
        }
        //关闭连接
        channel.close();
        connection.close();
    }
}
