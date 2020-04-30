package com.micro.common.utils;

import org.apache.commons.lang3.StringUtils;
import redis.clients.jedis.Jedis;

import java.util.UUID;

/**
 * 解决分布式锁
 * 只能单机
 */
public class RedisLock {

    private static final int LOCK_SUCCESS = 1;
    private static final String LOCK_KEY = "RED_PACK_LOCK";

    /**
     * 获取锁
     * @param lockKey   锁
     * @param ExpireTime    获取锁的超时时间
     * @param timeOut   锁的过期时间
     * @return
     */
    public static String getLock(String lockKey,int ExpireTime,int timeOut){

        //获取连接
        Jedis jedis = JedisUtil.getInstance().getJedis();

        //计算超锁的时间
        Long endTime = System.currentTimeMillis()+ExpireTime;

        //当前系统时间小于endTime说明获取锁没有超时
        while (System.currentTimeMillis() < endTime){
            String lockValue = UUID.randomUUID().toString();

            Long flag = jedis.setnx(lockKey, lockValue);//设置key

            if(flag == LOCK_SUCCESS){
                //设置有效期时间
                jedis.expire(lockKey,timeOut/1000);
                return lockValue;
            }
        }
        try {
            if (jedis != null){
                jedis.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public static void test_1(){
        String lock = getLock(LOCK_KEY, 5000, 100000);

        if(StringUtils.isEmpty(lock)){
            System.out.println(Thread.currentThread().getName()+"获取锁超时......"+lock);
            return;
        }
        System.out.println(Thread.currentThread().getName()+"获取锁成功..."+lock);
    }
    public static void main(String[] args) {
        for(int i=0;i<5;i++){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    test_1();
                }
            });
            thread.start();
        }

    }
}
