/*------------------------------------------------------------------------------
 * HelloController
 * @Description: 使用异步请求，提高系统的吞吐量
 * @author: haolin@pateo.com.cn
 * @date: 2018/11/21 11:04
 * @version V1.0
 * @Copyright 博泰悦臻网络技术服务有限公司
 *---------------------------------------------------------------------------*/
package org.andy.beans.async.example3.controller;


import org.andy.beans.async.example3.service.DemoAsyncService;
import org.andy.beans.async.example3.service.impl.HelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.WebAsyncTask;

import javax.annotation.Resource;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping(value = "/async")
public class HelloController {

    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private HelloService hello;

    @Resource
    private DemoAsyncService demoAsyncService;

    /**
     * 无异步请求的接口
     *
     * 请求从头到尾都只有一个线程，并且整个请求耗费了2s钟的时间。
     *
     * @return
     */
    @GetMapping("/helloworld")
    public String helloWorldController() {
        logger.info(Thread.currentThread().getName() + " 进入helloController方法");
        String say = hello.sayHello();
        logger.info(Thread.currentThread().getName() + " 从helloService方法返回");
        return say;
    }

    /**
     * 使用Callable异步请求的结果
     *
     *
     * 当controller返回值是Callable的时候，springmvc就会启动一个线程将Callable交给TaskExecutor去处理
     * 然后DispatcherServlet还有所有的spring拦截器都退出主线程，然后把response保持打开的状态
     * 当Callable执行结束之后，springmvc就会重新启动分配一个request请求，然后DispatcherServlet就重新
     * 调用和处理Callable异步执行的返回结果， 然后返回视图
     *
     *
     *
     * 容器的线程http-nio-8060-exec-1这个线程进入controller之后，
     * 就立即返回了，具体的服务调用是通过MvcAsync2这个线程来做的，
     * 当服务执行完要返回后，容器会再启一个新的线程http-nio-8060-exec-2来将结果返回给客户端或浏览器，
     * 整个过程response都是打开的，当有返回的时候，再从server端推到response中去。
     * @return
     */

    @RequestMapping(value = "/getTestDemoAsync", method = RequestMethod.GET)
    public Callable<Boolean> getEntityById() {
        logger.info("外部线程：" + Thread.currentThread().getName());
        return () -> {
            logger.info("内部线程：" + Thread.currentThread().getName());
            long start = System.currentTimeMillis();

            Future<String> task1 = demoAsyncService.doTaskOne();
            Future<String> task2 = demoAsyncService.doTaskTwo();
            Future<String> task3 = demoAsyncService.doTaskThree();
            System.out.println("main先执行--------");
            while (true) {
                if (task1.isDone() && task2.isDone() && task3.isDone()) {
                    // 三个任务都调用完成，退出循环等待，如果没有这个条件限制，主线程执行完，子线程并不会停止
                    break;
                }
                TimeUnit.SECONDS.sleep(5);
            }
            long end = System.currentTimeMillis();
            System.out.println("任务全部完成，总耗时：" + (end - start) + "毫秒");
            return true;
        };
    }


    @GetMapping("/hello")
    public Callable<String> helloController() {
        logger.info(Thread.currentThread().getName() + " 进入helloController方法");
        Callable<String> callable = () -> {
            logger.info(Thread.currentThread().getName() + " 进入call方法");
            String say = hello.sayHello();
            logger.info(Thread.currentThread().getName() + " 从helloService方法返回");
            return say;
        };
        logger.info(Thread.currentThread().getName() + " 从helloController方法返回");
        return callable;
    }


    /**
     * 带超时时间的异步请求 通过WebAsyncTask自定义客户端超时间
     *
     * @return
     */
    @GetMapping("/world")
    public WebAsyncTask<String> worldController() {
        logger.info(Thread.currentThread().getName() + " 进入helloController方法");

        // 3s钟没返回，则认为超时
        WebAsyncTask<String> webAsyncTask = new WebAsyncTask<>(3000, () -> {
            logger.info(Thread.currentThread().getName() + " 进入call方法");
            String say = hello.sayHello();
            TimeUnit.SECONDS.sleep(10);
            logger.info(Thread.currentThread().getName() + " 从helloService方法返回");
            return say;
        });

        logger.info(Thread.currentThread().getName() + " 从helloController方法返回");

        webAsyncTask.onCompletion(() -> logger.info(Thread.currentThread().getName() + " 执行完毕"));

        webAsyncTask.onTimeout(() -> {
            logger.info(Thread.currentThread().getName() + " onTimeout");
            // 超时的时候，直接抛异常，让外层统一处理超时异常
            throw new TimeoutException("调用超时");
        });
        return webAsyncTask;
    }


    /**
     * 异常统一被GlobalExceptionHandler处理
     * @return
     */
    @GetMapping("/exception")
    public WebAsyncTask<String> exceptionController() {
        logger.info(Thread.currentThread().getName() + " 进入helloController方法");
        Callable<String> callable = () -> {
            logger.info(Thread.currentThread().getName() + " 进入call方法");
            throw new TimeoutException("调用超时!");
        };
        logger.info(Thread.currentThread().getName() + " 从helloController方法返回");
        WebAsyncTask<String> webAsyncTask = new WebAsyncTask<>(2000, callable);
        return webAsyncTask;
    }
}

