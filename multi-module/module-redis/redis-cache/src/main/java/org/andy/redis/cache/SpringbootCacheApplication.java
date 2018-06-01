package org.andy.redis.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 缓存的产品很多种，本工程主要讲redis作为缓存工具
 * spring 内置缓存管理器
 * SimpleCacheManager
 * NoOpCacheManageer
 * ConcurrentMapCacheManager（默认）
 * CompositeCacheManager
 * EhCacheCacheManager
 * RedisCacheManager（当前使用）
 */
@SpringBootApplication
public class SpringbootCacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootCacheApplication.class, args);
    }
}
