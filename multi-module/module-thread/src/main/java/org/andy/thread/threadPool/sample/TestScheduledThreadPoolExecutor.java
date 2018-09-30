package org.andy.thread.threadPool.sample;

import java.util.Date;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * 创建一个大小无限的线程池。此线程池支持定时以及周期性执行任务的需求
 * @author: haolin@pateo.com.cn
 * @date: 2018/8/16 16:13
 * @version: V1.0
 */
public class TestScheduledThreadPoolExecutor {
    public static void main(String[] args) {
        ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(1);
        exec.scheduleAtFixedRate(new Runnable() {//每隔一段时间
            @Override
            public void run() {
                //throw new RuntimeException();
                System.out.println(new Date());
            }
            //启动延迟多少秒，每隔多久执行一次
        }, 1000, 5000, TimeUnit.MILLISECONDS);

        exec.scheduleAtFixedRate(new Runnable() {//每隔一段时间打印系统时间，证明两者是互不影响的
            @Override
            public void run() {
                System.out.println(new Date());
            }
        }, 1000, 2000, TimeUnit.MILLISECONDS);
    }
}
