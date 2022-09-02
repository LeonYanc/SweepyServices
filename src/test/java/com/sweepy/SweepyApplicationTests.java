package com.sweepy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.Jedis;

@SpringBootTest
class SweepyApplicationTests {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Test
    void contextLoads() {
    }

    @Test
    void testRedis() {

            redisTemplate.opsForValue().set("name","dadadingdada!");
            System.out.println(redisTemplate.opsForValue().get("name"));
        }


}
