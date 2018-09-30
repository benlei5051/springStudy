package org.andy.redis.lock.example1.service;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 *
 * @author: haolin@pateo.com.cn
 * @date: 2018/9/28 11:26
 * @version: V1.0
 */
public class Worker implements Runnable {

    private final CountDownLatch startSignal;
    private final CountDownLatch doneSignal;
    private final DistributedLocker distributedLocker;

    public Worker(CountDownLatch startSignal, CountDownLatch doneSignal, DistributedLocker distributedLocker) {
        this.startSignal = startSignal;
        this.doneSignal = doneSignal;
        this.distributedLocker = distributedLocker;
    }

    @Override
    public void run() {
        try {
            //让所有的子线程睡眠，当startSignal的值为0时，五个子线程同时被唤醒，起到同时执行下面代码的目的
            startSignal.await();
            distributedLocker.lock("test", new AquiredLockWorker<Object>() {

                @Override
                public Object invokeAfterLockAquire() {
                    doTask();
                    return null;
                }

            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void doTask() {
        System.out.println(Thread.currentThread().getName() + " start");
        Random random = new Random();
        int _int = random.nextInt(200);
        System.out.println(Thread.currentThread().getName() + " sleep " + _int + "millis");
        try {
            Thread.sleep(_int);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " end");
        doneSignal.countDown();
    }
}
