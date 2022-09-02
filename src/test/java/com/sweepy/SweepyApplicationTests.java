package com.sweepy;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;

@SpringBootTest
class SweepyApplicationTests {
    @Test
    void contextLoads() {
    }

    @Test
    void testRedis() {
        Jedis jedis = new Jedis("localhost", 6379);
        jedis.auth("mypass");
        System.out.println("Connected to Redis");

    }

}
