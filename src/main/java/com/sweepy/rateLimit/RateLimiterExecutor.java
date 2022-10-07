package com.sweepy.rateLimit;

import com.google.common.util.concurrent.RateLimiter;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RateLimiterExecutor {
    @Getter
    private final RateLimitCounter rateLimitCounter;

    @Autowired
    public RateLimiterExecutor(RateLimitCounter rateLimitCounter) {
        this.rateLimitCounter = rateLimitCounter;
    }

    public static boolean isRateLimited(RateLimiter rateLimiter, int period, int permits) {
        boolean tryAcquire = rateLimiter.tryAcquire(permits, (long) period, TimeUnit.SECONDS);
        return !tryAcquire;
    }
}
