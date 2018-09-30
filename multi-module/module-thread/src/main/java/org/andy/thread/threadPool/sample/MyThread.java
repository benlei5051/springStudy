package org.andy.thread.threadPool.sample;

/**
 * @Description: TODO
 * @author: haolin@pateo.com.cn
 * @date: 2018/8/16 16:06
 * @version: V1.0
 */
public class MyThread extends Thread{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "正在执行...");
    }
}
