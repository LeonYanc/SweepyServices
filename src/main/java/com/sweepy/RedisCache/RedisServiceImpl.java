package com.sweepy.RedisCache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl implements RedisService {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Override
    public void setLTS(String longUrl, String shortUrl, long time) {
        redisTemplate.opsForValue().set(longUrl, shortUrl, time, TimeUnit.MINUTES);
    }

    @Override
    public void setSTL(String longUrl, String shortUrl, long time) {
        redisTemplate.opsForValue().set(shortUrl, longUrl, time, TimeUnit.MINUTES);
    }

    @Override
    public void expire(String key, long time) {
        redisTemplate.expire(key, time, TimeUnit.MINUTES);

    }

    @Override
    public long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.MINUTES);
    }

    @Override
    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public void set(String key, String value, long time) {
        redisTemplate.opsForValue().set(key, value, time, TimeUnit.MINUTES);
    }

    @Override
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }
}
