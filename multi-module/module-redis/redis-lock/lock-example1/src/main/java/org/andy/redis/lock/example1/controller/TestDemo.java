package org.andy.redis.lock.example1.controller;

import org.andy.redis.lock.example1.service.impl.DistributedLockerImpl;
import org.andy.redis.lock.example1.service.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CountDownLatch;

/**
 *
 * @author: haolin@pateo.com.cn
 * @date: 2018/9/28 11:19
 * @version: V1.0
 */

@RestController
public class TestDemo {
    @Autowired
    DistributedLockerImpl distributedLocker;

    @RequestMapping(value = "/redlock")
    public String testRedlock() throws Exception{

        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(2);
        // create and start threads
        for (int i = 0; i < 2; ++i) {
            new Thread(new Worker(startSignal, doneSignal, distributedLocker)).start();
        }
        // let all threads proceed
        startSignal.countDown();
        doneSignal.await();
        System.out.println("All processors done. Shutdown connection");
        return "redlock";
    }
}




