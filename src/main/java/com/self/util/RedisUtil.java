package com.self.util;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;

import java.util.concurrent.TimeUnit;

public class RedisUtil {
    private RedisTemplate<String, Object> redisTemplate;

    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    public boolean expire(String key, long time){
        redisTemplate.expire(key, time, TimeUnit.SECONDS);
        return true;
    }

    public long getExpire(String key){
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    //======================string=================================
    public boolean set(String key, Object value){
        redisTemplate.opsForValue().set(key, value);
        return true;
    }

    public Object get(String key){
        return redisTemplate.opsForValue().get(key);
    }

    public void del(String ...key){
        if (key != null && key.length > 0){
            redisTemplate.delete(CollectionUtils.arrayToList(key));
        }
    }

    public long incr(String key, long delta){
        return redisTemplate.opsForValue().increment(key, delta);
    }

    public long decr(String key, long delta){
        return redisTemplate.opsForValue().increment(key, -delta);
    }

    //=======================list========================================

    //=======================set========================================

    //=======================map========================================
}
