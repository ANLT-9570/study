package com.micro.MQTest.routing_direct.producer;

import com.micro.MQTest.MQConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/*
* 生产者 交换机类型direct类型
* */
public class ProducerDirect {

    //交换机名称
    private static final String DESTINATION_NAME = "my_direct";

    public static void main(String[] args) throws IOException, TimeoutException {
        //建立mq连接
        Connection connection = MQConnectionUtils.newConnection();

        //创建通道
        Channel channel = connection.createChannel();

        //生产者绑定交换机 参数1.交换机名称2.交换机类型
        channel.exchangeDeclare(DESTINATION_NAME,"direct");
        //路由键
        String routingKey = "direct_2";
        //创建消息
        String msg = "2020-05-05*-/*-/*-/-*-Producer"+routingKey;

        //发送消息
        channel.basicPublish(DESTINATION_NAME,routingKey,null,msg.getBytes());

        channel.close();
        connection.close();
    }

}
