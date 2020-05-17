package com.micro.MQTest.topic;

import com.micro.MQTest.MQConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/*
* 生产者 交换机类型topic类型
* */
public class ProducerTopic {

    //交换机名称
    private static final String DESTINATION_NAME = "my_topic";

    public static void main(String[] args) throws IOException, TimeoutException {
        //建立mq连接
        Connection connection = MQConnectionUtils.newConnection();

        //创建通道
        Channel channel = connection.createChannel();

        //生产者绑定交换机 参数1.交换机名称2.交换机类型
        channel.exchangeDeclare(DESTINATION_NAME,"topic");

        try {
            //开启事务
            channel.txSelect();
            //路由键
            String routingKey = "topic_1.town";
            for(int i=0;i<100;i++){
                //创建消息
                String msg = i+"--2020-05-05*-/*-/*-/-*-Producer"+routingKey;

                //发送消息
                channel.basicPublish(DESTINATION_NAME,routingKey,null,msg.getBytes());
                if(i==99){
//                    int a = 1/0;
                }
            }
            //提交事务
            channel.txCommit();
        }catch (Exception e){
            e.printStackTrace();
            //事务回滚
            channel.txRollback();
            System.out.println("回滚了....");
        }finally {
            channel.close();
            connection.close();
        }

    }

}
