package org.andy.thread.threadWait;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author: haolin@pateo.com.cn
 * @date: 2018/10/9 9:53
 * @version: V1.0
 */
public class TestDemo2 {
        public static void main(String[] args) throws InterruptedException {
            ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5, 10, 100, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(5));
            int count = 10;
            //countDown方法，当前线程调用此方法，则计数减一
            //await方法，调用此方法会一直阻塞当前线程，直到计时器的值为0
            final CountDownLatch latch = new CountDownLatch(count);

            for (int i = 0; i < count; i++) {
                threadPool.execute(new MyRunnable1(latch, i));
            }

            latch.await();
            System.err.println("等待线程被唤醒！");
            threadPool.shutdown();
        }
    }

    class MyRunnable1 implements Runnable {

        CountDownLatch latch;
        int i;

        public MyRunnable1(CountDownLatch latch, int i) {
            this.latch = latch;
            this.i = i;
        }

        @Override
        public void run() {
            System.err.println("线程" + i +"完成了操作...");
            try {
                Thread.currentThread();
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            latch.countDown();
        }

    }