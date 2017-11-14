package org.andy.thread.futureCallable;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * @author: andy
 * @Date: 2017/11/13 16:49
 * @Description: 线程池可以提交FutureTask和Callable接口
 * 如果传入的是FutureTask，那么结果需要用FutrueTask.get()获取
 * 如果传入的是 Callable，那么结果需要用ExecutorService.submit(Callable<?> callable)返回的Future接口的get()方法获取结果
 */
public class FutureTaskDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        FutureTask<Integer> futureTask = new FutureTask(new Task());
        executorService.submit(futureTask);
        executorService.shutdown();
        System.out.println("主线程在执行任务");
        try {
            //一直会等待拿到结果，导致线程阻塞
            System.out.println("task运行结果" + futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("所有任务执行完毕");

    }


}
