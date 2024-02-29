package com.example.agencyamazontest.config;

import java.util.Arrays;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableCaching
@Configuration
public class SpringCashConfiguration {
    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(Arrays.asList(
                new ConcurrentMapCache("salesAndTrafficByAsins"),
                new ConcurrentMapCache("salesAndTrafficByDates"),
                new ConcurrentMapCache("salesAndTrafficByAsin"),
                new ConcurrentMapCache("salesAndTrafficByDate")));
        return cacheManager;
    }
}
