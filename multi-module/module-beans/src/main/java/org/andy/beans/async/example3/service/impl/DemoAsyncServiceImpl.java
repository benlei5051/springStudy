package org.andy.beans.async.example3.service.impl;

import org.andy.beans.async.example3.service.DemoAsyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.Future;

@Service
public class DemoAsyncServiceImpl implements DemoAsyncService {
    private static final Logger logger = LoggerFactory.getLogger(DemoAsyncServiceImpl.class);

    public static Random random = new Random();

    /**
     *
     * 默认的线程池
     *
     * 不可以在本类中直接用this.doTaskOne()调用，而需要在外部类中调用才会生效
     *
     * 注意，这里的异步方法，只能在自身之外调用，在本类调用是无效的
     *
     *
     * @return
     * @throws Exception
     */
    @Override
    @Async
    public Future<String> doTaskOne() throws Exception {
        logger.info("开始做任务一");
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
        }
        Thread.sleep(random.nextInt(20000));
        long end = System.currentTimeMillis();
        System.out.println(String.format("任务执行成功,耗时{%s}", end - start));
        System.out.println("完成任务一，耗时：" + (end - start) + "毫秒");
        return new AsyncResult<>("任务一完成");
    }

    @Override
    @Async
    public Future<String> doTaskTwo() throws Exception {
        logger.info("开始做任务二");
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
        }
        Thread.sleep(random.nextInt(20000));
        long end = System.currentTimeMillis();
        System.out.println("完成任务二，耗时：" + (end - start) + "毫秒");
        return new AsyncResult<>("任务二完成");
    }

    @Override
    @Async
    public Future<String> doTaskThree() throws Exception {
        logger.info("开始做任务三");
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
        }
        Thread.sleep(random.nextInt(20000));
        long end = System.currentTimeMillis();
        System.out.println("完成任务三，耗时：" + (end - start) + "毫秒");
        return new AsyncResult<>("任务三完成");
    }
}
