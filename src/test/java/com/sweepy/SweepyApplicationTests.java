package com.sweepy;

import com.sweepy.RedisCache.SequenceIdService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import redis.clients.jedis.Jedis;

@SpringBootTest
class SweepyApplicationTests {

    @Mock
    private RedisTemplate<String, Long> redisTemplateId;

    @Autowired
    @Qualifier("counterScript")
    private RedisScript<Long> redisCounterScript;

    @Test
    void contextLoads() {
    }

    @Test
    void testRedis() {

        SequenceIdService ser = new SequenceIdService(redisTemplateId);
        Long Id = ser.getNextSequenceByLua();
        //Long Id = ser.getNextSequenceIdbyAtomic();
        System.out.println(Id);
        }


}
