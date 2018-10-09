package org.andy.thread.threadWait;

import java.util.concurrent.CountDownLatch;

/**
 *   16个线程在同一时间起步跑，当16个线程都执行完各自的任务，再执行主线程
 *
 * @author: haolin@pateo.com.cn
 * @date: 2018/10/9 13:55
 * @version: V1.0
 */
public class TestDemo3 {
    public static void main(String[] args) throws InterruptedException {
        // 初始化计数器为 一
        CountDownLatch start = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(16);
        //模擬16个线程
        for (int i = 0; i < 16; i++) {
            MyTestThread tt = new MyTestThread(start, doneSignal);
            Thread t = new Thread(tt);
            t.start();
        }
        start.countDown();//计数器減一  所有线程释放 同时跑
        doneSignal.await();
        System.out.println("All processors done. Shutdown connection");
    }
}

class MyTestThread implements Runnable {
    private final CountDownLatch startSignal;
    private final CountDownLatch doneSignal;

    public MyTestThread(CountDownLatch startSignal, CountDownLatch doneSignal) {
        super();
        this.startSignal = startSignal;
        this.doneSignal = doneSignal;
    }

    @Override
    public void run() {
        try {
            //一直阻塞当前线程，直到计时器的值为0
            startSignal.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        doWork();
        doneSignal.countDown();

    }

    private void doWork() {
        System.out.println(Thread.currentThread().getId() + " do work");
    }
}