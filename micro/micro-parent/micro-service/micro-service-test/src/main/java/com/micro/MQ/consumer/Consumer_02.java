package com.micro.MQ.consumer;

import com.micro.MQ.MQConnectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/*
* 工作队列消费者
* */
public class Consumer_02 {
    //队列名称
    private static final String QUEUE_NAME = "NAME_xc";

    public static void main(String[] args) throws IOException, TimeoutException {
        System.out.println("消费者............2");
        //创建一个连接
        Connection connection = MQConnectionUtils.newConnection();

        //创建通道
        Channel channel = connection.createChannel();

        //消费者关联队列   创建一个队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        channel.basicQos(1);
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {

                String s = new String(body, "utf-8");
                System.out.println("msg..."+s);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    //                设置手动应答
                    channel.basicAck(envelope.getDeliveryTag(),false);
                }

            }
        };

//        设置应答模式为true表示自动应答false表示手动应答
        channel.basicConsume(QUEUE_NAME,false,defaultConsumer);

//        channel.close();
//        connection.close();

    }
}
