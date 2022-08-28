package com.sweepy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import javax.persistence.Cacheable;

@EnableCaching
@SpringBootApplication
public class SweepyApplication {

    public static void main(String[] args) {
        SpringApplication.run(SweepyApplication.class, args);

    }

}
