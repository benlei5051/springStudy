package org.andy.thread.util;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author: haolin@pateo.com.cn
 * @date: 2018/9/19 18:23
 * @version: V1.0
 */
public class AtomicIntegerTest {
    /**
     *  使用原子操作类
     */
    private static AtomicInteger num = new AtomicInteger(0);
    //使用CountDownLatch来等待计算线程执行完
    private static CountDownLatch countDownLatch = new CountDownLatch(3);

    public static void main(String []args) throws InterruptedException {
        //开启30个线程进行累加操作
        for(int i=0;i<3;i++){
            new Thread(){
                @Override
                public void run(){
                    for(int j=0;j<10000;j++){
                        num.incrementAndGet();//原子性的num++,通过循环CAS方式
                    }
                    countDownLatch.countDown();
                }
            }.start();
        }
        //等待计算线程执行完
        countDownLatch.await();
        System.out.println(num);
    }
}
