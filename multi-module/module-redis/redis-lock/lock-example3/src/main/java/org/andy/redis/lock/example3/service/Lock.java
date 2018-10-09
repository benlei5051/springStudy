package org.andy.redis.lock.example3.service;

/**
 *
 * 全局锁，包括锁的名称
 * @author: haolin@pateo.com.cn
 * @date: 2018/10/9 10:25
 * @version: V1.0
 */

public class Lock {
    private String name;
    private String value;

    public Lock(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

}

