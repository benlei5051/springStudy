package org.andy.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: andy
 * @Date: 2017/9/26 11:41
 * @Description:
 */

/**
 * 线程安全    atomic 相当于synchronize加锁
 */
class MyThread implements Runnable {
    //    static  int i = 0;
    static AtomicInteger ai=new AtomicInteger(0);


    public void run() {
        for (int m = 0; m < 1000000; m++) {
            ai.getAndIncrement();
        }
    }
};

public class TestAtomicInteger {
    public static void main(String[] args) throws InterruptedException {
        MyThread mt = new MyThread();

        Thread t1 = new Thread(mt);
        Thread t2 = new Thread(mt);
        t1.start();
        t2.start();
        Thread.sleep(500);
        System.out.println(MyThread.ai.get());
    }
}
