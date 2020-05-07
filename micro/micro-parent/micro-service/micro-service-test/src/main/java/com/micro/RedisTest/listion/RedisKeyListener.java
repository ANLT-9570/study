package com.micro.RedisTest.listion;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;
/*
* 监听redis的key是否过期
* */
@Component
public class RedisKeyListener extends KeyExpirationEventMessageListener {


    public RedisKeyListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {

        System.out.println("过期了..."+message.toString());

    }
}
