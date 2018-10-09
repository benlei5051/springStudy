package org.andy.thread.threadPool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * newSingleThreadExecutor：创建一个单线程的线程池。这个线程池只有一个线程在工作，也就是相当于单线程串行执行所有任务。如果这个唯一的线程因为异常结束，那么会有一个新的线程来替代它。此线程池保证所有任务的执行顺序按照任务的提交顺序执行。
 *
 * newFixedThreadPool：创建固定大小的线程池。每次提交一个任务就创建一个线程，直到线程达到线程池的最大大小。线程池的大小一旦达到最大值就会保持不变，如果某个线程因为执行异常而结束，那么线程池会补充一个新线程。
 *
 * newCachedThreadPool：创建一个可缓存的线程池。如果线程池的大小超过了处理任务所需要的线程，那么就会回收部分空闲（60秒不执行任务）的线程，当任务数增加时，此线程池又可以智能的添加新线程来处理任务。此线程池不会对线程池大小做限制，线程池大小完全依赖于操作系统（或者说JVM）能够创建的最大线程大小。
 *
 * newScheduledThreadPool：创建一个大小无限的线程池。此线程池支持定时以及周期性执行任务的需求。
 *
 * newSingleThreadScheduledExecutor：创建一个单线程的线程池。此线程池支持定时以及周期性执行任务的需求。
 *
 */
public class ThreadPoolDemo {

    public static void main(String[] args)throws Exception {
//        testSingleFutureAndCallable();
//        testMultFutureAndCallable();
        testSuggestPool();
    }

    /**
     *测试只有一个线程一个任务的Future
     */
    public static void testSingleFutureAndCallable()throws Exception{
        ExecutorService service = Executors.newSingleThreadExecutor();

        Future<String> future = service.submit(() -> {
            Thread.sleep(3000);
            return "Hello";
        });

        System.out.println("等待拿到结果：");
        System.out.println(future.get());
        service.shutdown();

    }

    public static void testSuggestPool() {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("demo-pool-%d").build();

        //Common Thread Pool
        ExecutorService pool = new ThreadPoolExecutor(55, 200,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
        for (int i = 0; i < 5; i++) {
            pool.submit(()-> System.out.println(Thread.currentThread().getName()));
        }
        pool.shutdown();
    }
    /**
     * 提交一组callable，谁先完成就拿谁的结果
     * @throws Exception
     */
    public static void testMultFutureAndCallable()throws Exception{
        //新建了一个固定线程池
        ExecutorService service = Executors.newFixedThreadPool(10);
        //CompletionService 可以将已完成任务与未完成的任务分离出来 ExecutorCompletionService此类将安排那些完成时提交的任务，把它们放置在可使用 take 访问的队列上
        CompletionService<String> completionService = new ExecutorCompletionService<String>(service);
        //加入10个输出字符串的任务，并使每个任务随机停留时间在3秒内
        for(int i=0;i<10;i++){
            final int num = i;
            completionService.submit(new Callable<String>(){
                @Override
                public String call() throws Exception {
                    Thread.sleep(new Random().nextInt(3000));
                    return "hello"+num;
                }
            });
        }
        //获取任务的结果
        for(int j=0;j<10;j++){

            Future<String> future =completionService.take();
            System.out.println(future.get());
        }
        //关闭线程池
        service.shutdown();

    }

}