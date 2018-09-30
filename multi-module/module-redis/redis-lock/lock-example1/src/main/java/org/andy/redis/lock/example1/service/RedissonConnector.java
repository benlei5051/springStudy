package org.andy.redis.lock.example1.service;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 *获取RedissonClient连接类
 *
 * @author: haolin@pateo.com.cn
 * @date: 2018/9/28 11:06
 * @version: V1.0
 */

@Component
public class RedissonConnector {

    private RedissonClient redissonClient;

    @PostConstruct
    public void init() {
        System.out.println("-----------------------");
        redissonClient = Redisson.create();
    }

    public RedissonClient getClient() {
        return redissonClient;
    }
}
