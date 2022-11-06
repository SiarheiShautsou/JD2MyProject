package com.sheva.configuration;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class CacheConfig {

    @Bean
    public CacheManager cacheManager() {

        CaffeineCacheManager cacheManager = new CaffeineCacheManager("training types");
        cacheManager.setCaffeine(cacheProperties());
        return cacheManager;

    }

    public Caffeine<Object, Object> cacheProperties() {

        return Caffeine.newBuilder()
                .initialCapacity(20)
                .maximumSize(30)
                .expireAfterAccess(10, TimeUnit.DAYS)
                .weakKeys()
                .recordStats();
    }
}
