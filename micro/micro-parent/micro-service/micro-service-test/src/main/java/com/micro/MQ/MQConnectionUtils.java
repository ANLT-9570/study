package com.micro.MQ;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class MQConnectionUtils {

    public static Connection newConnection() throws IOException, TimeoutException {
        //创建工厂连接
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setVirtualHost("/virtual");
        factory.setPort(5672);
        return factory.newConnection();
    }

}
