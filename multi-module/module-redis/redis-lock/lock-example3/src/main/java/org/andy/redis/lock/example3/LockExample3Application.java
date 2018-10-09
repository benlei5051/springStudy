package org.andy.redis.lock.example3;

import org.andy.redis.lock.example3.service.DistributedLockHandler;
import org.andy.redis.lock.example3.service.Lock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author: haolin@pateo.com.cn
 * @date: 2018/10/9 10:24
 * @version: V1.0
 */
@RestController
@SpringBootApplication
public class LockExample3Application {
    @Autowired DistributedLockHandler distributedLockHandler;

    public static void main(String[] args) {
       SpringApplication.run(LockExample3Application.class, args);

    }

    @GetMapping("/test")
    public void testDistributeLock() {
        Lock lock=new Lock("lockk","sssssssss");
        if(distributedLockHandler.tryLock(lock)){
            System.out.println("----------------执行业务逻辑");
            distributedLockHandler.releaseLock(lock);
        }
    }

}
