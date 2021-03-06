package com.micro.MQTest.fanout.consumer;

import com.micro.MQTest.MQConnectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/*
* 消费者2
* */
public class ConsumerFanout_2 {
    //消费者队列名称
    private static final String QUEUE_NAME = "consumer_fanout_2";
    //交换机名称
    private static final String DESTINATION_NAME = "my_fanout";

    public static void main(String[] args) throws IOException, TimeoutException {
        System.out.println("消费者启动---*****----222");
        //建立mq连接
        Connection connection = MQConnectionUtils.newConnection();

        //创建通道
        Channel channel = connection.createChannel();
        //消费者声明队列 2参数为true表示持久化到硬盘
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //消费者队列绑定交换机
        channel.queueBind(QUEUE_NAME,DESTINATION_NAME,"");
        //消费者监听消息
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {

                String msg = new String(body, "utf-8");
                System.out.println("消费者---222" + msg);


            }
        };
        //设置应答模式为true表示自动应答false表示手动应答
        channel.basicConsume(QUEUE_NAME,true,defaultConsumer);
    }
}
