package org.andy.thread;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args)throws Exception {
     //   testSingleFutureAndCallable();
        testMultFutureAndCallable();
    }

    /**
     *测试只有一个线程一个任务的Future
     */
    public static void testSingleFutureAndCallable()throws Exception{
        ExecutorService service = Executors.newSingleThreadExecutor();

        Future<String> future = service.submit(new Callable<String>(){
            @Override
            public String call() throws Exception {
                Thread.sleep(3000);
                return "Hello";
            }
        });

        System.out.println("等待拿到结果：");
        System.out.println(future.get());
        service.shutdown();

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