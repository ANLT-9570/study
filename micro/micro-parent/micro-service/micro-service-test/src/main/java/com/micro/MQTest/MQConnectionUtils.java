package com.micro.MQTest;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class MQConnectionUtils {

    public static Connection newConnection() throws IOException, TimeoutException {
        //创建工厂连接
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("120.79.42.229");
        factory.setUsername("admin");
        factory.setPassword("123456");
        factory.setVirtualHost("/xc");
        factory.setPort(5672);
        return factory.newConnection();
    }

}
