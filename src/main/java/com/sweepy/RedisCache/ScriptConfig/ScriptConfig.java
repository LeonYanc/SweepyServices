package com.sweepy.RedisCache.ScriptConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.redis.core.script.RedisScript;

@Configuration
public class ScriptConfig {
    public ScriptConfig () {

    }
    @Bean(name="counterScript")
    public RedisScript<Long> counterScript() {
        Resource scriptSource = new ClassPathResource("redisCounter.lua");
        return RedisScript.of(scriptSource, long.class);
    }
}
