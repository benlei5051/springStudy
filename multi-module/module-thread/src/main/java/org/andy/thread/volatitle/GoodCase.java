package org.andy.thread.volatitle;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: andy
 * @Date: 2017/11/14 11:15
 * @Description:
 */

public class GoodCase {

    public  static AtomicInteger count = new AtomicInteger(0);
 //   public volatile static AtomicInteger count = new AtomicInteger(0);

    public static void inc() {

        //这里延迟1毫秒，使得结果明显
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
        }

        count.getAndIncrement();
    }

    public static void main(String[] args) {

        //同时启动1000个线程，去进行i++计算，看看实际结果

        for (int i = 0; i < 1000; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    GoodCase.inc();
                }
            }).start();
        }
        while (Thread.activeCount()>2){
            //主线程让出所有的子线程去执行任务，当结果值=2时，子线程都执行完毕
            System.out.println(Thread.activeCount()+"--------------------");
            Thread.yield();
        }
        //这里每次运行的值都有可能不同,可能为1000
        System.out.println("运行结果:Counter.count=" + GoodCase.count.get());
    }
}