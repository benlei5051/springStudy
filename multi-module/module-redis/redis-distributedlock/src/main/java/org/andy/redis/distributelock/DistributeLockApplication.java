package org.andy.redis.distributelock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: andy
 * @Date: 2018/7/24 17:08
 * @Description:
 */
@SpringBootApplication
public class DistributeLockApplication {
    public static void main(String[] args) {
        SpringApplication.run(DistributeLockApplication.class, args);
    }
}
