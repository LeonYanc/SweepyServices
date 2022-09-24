package com.sweepy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.persistence.Cacheable;

@EnableCaching
@SpringBootApplication
public class SweepyApplication {

    public static void main(String[] args) {
        SpringApplication.run(SweepyApplication.class, args);

    }


}
