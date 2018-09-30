package org.andy.thread.threadPool.sample;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description:
 *
 * 创建一个可缓存的线程池。如果线程池的大小超过了处理任务所需要的线程，
 * 那么就会回收部分空闲（60 秒不执行任务）的线程，当任务数增加时，此线程池又可
 * 以智能的添加新线程来处理任务。此线程池不会对线程池大小做限制，线程池大小完全
 * 依赖于操作系统（或者说 JVM）能够创建的最大线程大小.
 * @author: haolin@pateo.com.cn
 * @date: 2018/8/16 16:10
 * @version: V1.0
 */
public class TestCachedThreadPool {
    public static void main(String[] args){
        ExecutorService pool = Executors.newCachedThreadPool();
//创建实现了 Runnable 接口对象， Thread 对象当然也实现了 Runnable 接口
        Thread t1 = new MyThread();
        Thread t2 = new MyThread();
        Thread t3 = new MyThread();
        Thread t4 = new MyThread();
        Thread t5 = new MyThread();
//将线程放入池中进行执行
        pool.execute(t1);
        pool.execute(t2);
        pool.execute(t3);
        pool.execute(t4);
        pool.execute(t5);
//关闭线程池
        pool.shutdown();
    }
}
