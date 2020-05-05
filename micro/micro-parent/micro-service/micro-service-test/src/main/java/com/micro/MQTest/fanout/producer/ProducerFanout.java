package com.micro.MQTest.fanout.producer;

import com.micro.MQTest.MQConnectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/*
* 生产者 交换机类型fanout类型
* */
public class ProducerFanout {

    //交换机名称
    private static final String DESTINATION_NAME = "my_fanout";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        //建立mq连接
        Connection connection = MQConnectionUtils.newConnection();

        //创建通道
        Channel channel = connection.createChannel();

        //生产者绑定交换机 参数1.交换机名称2.交换机类型
        channel.exchangeDeclare(DESTINATION_NAME,"fanout");

        //创建消息
        String msg = "2020-05-05*-/*-/*-/-*-Producer";
        //发送消息
        channel.basicPublish(DESTINATION_NAME,"", MessageProperties.PERSISTENT_BASIC,msg.getBytes());
        channel.addConfirmListener(new ConfirmListener() {
            @Override
            public void handleAck(long l, boolean b) throws IOException {
                System.out.println(l+"----"+b);
            }

            @Override
            public void handleNack(long l, boolean b) throws IOException {
                System.out.println(l+"----"+b);
            }
        });
        channel.addReturnListener(new ReturnListener() {
            @Override
            public void handleReturn(int i, String s, String s1, String s2, AMQP.BasicProperties basicProperties, byte[] bytes) throws IOException {
                System.out.println(i+"----"+s+s1+s2);
            }
        });
//        if(channel.waitForConfirms()){
//            System.out.println("消息发送成功....");
//        }else{
//            System.out.println("消息发送失败.....ERROR....");
//        }
        channel.close();
        connection.close();
    }

}
