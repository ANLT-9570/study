package com.micro.MQTest.peerToPeer.consumer;

import com.micro.MQTest.MQConnectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/*
* 点对消费者
* */
public class Consumer_01 {
    //队列名称
    private static final String QUEUE_NAME = "NAME_xc";

    public static void main(String[] args) throws IOException, TimeoutException {
        System.out.println("消费者............2");
        //创建一个连接
        Connection connection = MQConnectionUtils.newConnection();

        //创建通道
        Channel channel = connection.createChannel();

        //消费者关联队列   创建一个队列 2参数为true表示持久化到硬盘
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        DefaultConsumer defaultConsumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {

                String s = new String(body, "utf-8");
                System.out.println("msg..."+s);

//                设置手动应答
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        };

//        设置应答模式为true表示自动应答false表示手动应答
        channel.basicConsume(QUEUE_NAME,true,defaultConsumer);

//        channel.close();
//        connection.close();

    }
}
