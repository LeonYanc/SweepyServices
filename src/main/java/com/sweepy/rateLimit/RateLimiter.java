package com.sweepy.rateLimit;

public interface RateLimiter {
    boolean isRateLimited(String key, RateVariables rateVariables);
}
