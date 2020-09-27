package com.example.sell.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@Slf4j
public class RedisUtil {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //加锁
    public boolean lock(String productId,String time){
        //对应setnx命令，如果productId为空，才会设置time，否则什么都不做
        //true 表示可以设置，productId为空
        if(stringRedisTemplate.opsForValue().setIfAbsent(productId,time)){
            return true;
        }
        //如果productId不为空，可能time是过期的，需要判断
        String expireTime = stringRedisTemplate.opsForValue().get(productId);
        //如果不为空 且 当前时间超过了过期时间，表示已经过期了
            if(!StringUtils.isEmpty(expireTime)&& Long.parseLong(expireTime)<System.currentTimeMillis()){
            //获取旧的时间  并设置新的时间
            String oldTime = stringRedisTemplate.opsForValue().getAndSet(productId, time);
            //判断是否和原来时间一致，不一致表示另一个线程已经修改过了,加锁失败
            // 如果一致加锁成功
            if(!StringUtils.isEmpty(oldTime) && oldTime.equals(expireTime)){
                return true;
            }
        }
         return false;
        }

        //解锁
    public void unlock(String productId,String time){
        try {
            String expireTime = stringRedisTemplate.opsForValue().get(productId);
            if(!StringUtils.isEmpty(expireTime) && expireTime.equals(time)){
                stringRedisTemplate.opsForValue().getOperations().delete(productId);
            }
        } catch (Exception e) {
            log.info("【分布式Redis锁】解锁异常：{}",e);
        }

    }
}
































