package com.sweepy.RedisCache;

import com.sweepy.RedisCache.ScriptConfig.ScriptConfig;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
@Service
public class SequenceIdService {
    private String GLOBAL_SEQUENCE_ID = "Global_Sequence_ID";

    private String SEQUENCE_ID = "Sequence_ID";

    private ScriptConfig config = new ScriptConfig();
    private RedisScript<Long> redisCounterScript = config.counterScript();

    private RedisAtomicLong entityCounter;
    private RedisTemplate<String, Long> sequenceIdTemplate;

    @Autowired
    public SequenceIdService(RedisTemplate<String, Long> sequenceIdTemplate) {
        this.sequenceIdTemplate = sequenceIdTemplate;
    }

    public long getNextSequenceIdbyAtomic() {
        entityCounter = new RedisAtomicLong(SEQUENCE_ID, sequenceIdTemplate.getConnectionFactory());
        long increment = entityCounter.getAndIncrement();
        sequenceIdTemplate.getConnectionFactory().getConnection().bgSave();
        return increment;
    }

    public long getNextSequenceByLua() {
        System.out.println(redisCounterScript.getScriptAsString());
        long sequenceId = sequenceIdTemplate.execute(redisCounterScript, List.of(GLOBAL_SEQUENCE_ID));
        sequenceIdTemplate.getConnectionFactory().getConnection().bgSave();
        return sequenceId;
    }
}
