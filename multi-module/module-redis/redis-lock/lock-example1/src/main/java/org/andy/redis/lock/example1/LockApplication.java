package org.andy.redis.lock.example1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @author: haolin@pateo.com.cn
 * @date: 2018/9/28 16:02
 * @version: V1.0
 */
@SpringBootApplication
public class LockApplication {
    public static void main(String[] args) {
        SpringApplication.run(LockApplication.class, args);
    }
}
