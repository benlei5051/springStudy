package org.andy.microservice.spring.retry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.CircuitBreaker;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;

import java.util.concurrent.TimeUnit;

/**
 *
 * @author: haolin@pateo.com.cn
 * @date: 2018/10/15 16:56
 * @version: V1.0
 */
@SpringBootApplication
@EnableRetry
public class SpringbootRetryApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootRetryApplication.class, args);
    }
    @EventListener
    public void contextEvent(ContextRefreshedEvent contextEvent) throws InterruptedException {
        SpringbootRetryApplication demoApplication=contextEvent.getApplicationContext().getBean(SpringbootRetryApplication.class);
        System.err.println("尝试进入断路器方法，并触发异常");

        demoApplication.circuitBreaker(1);
//        demoApplication.circuitBreaker(1);
//        demoApplication.circuitBreaker(1);

        System.err.println("尝试进入断路器方法，在openTimeout时间内，触发异常超过3次，断路器打开，断路器方法不允许执行，直接执行恢复方法");
//        demoApplication.circuitBreaker(2);
        TimeUnit.SECONDS.sleep(20);
        System.err.println("超过断路器半开时间resetTimeout，断路器半开，断路器方法运行允许3个访问进入");
        demoApplication.circuitBreaker(3);
        demoApplication.circuitBreaker(3);
        demoApplication.circuitBreaker(3);
//        System.err.println("尝试进入断路器方法，在openTimeout时间内，触发异常超过3次，断路器打开，断路器方法不允许执行，直接执行恢复方法");
//        demoApplication.circuitBreaker(4);
//        TimeUnit.SECONDS.sleep(3);
//        System.err.println("超过断路器再次超过半开时间openTimeout+resetTimeout，断路器半开，断路器方法运行允许三个访问进入");
//        demoApplication.circuitBreaker(5);
//        demoApplication.circuitBreaker(5);
//        demoApplication.circuitBreaker(5);
//        System.err.println("尝试进入断路器方法，在openTimeout时间内，触发异常超过3次，断路器打开，断路器方法不允许执行，直接执行恢复方法");
//        demoApplication.circuitBreaker(6);
//        TimeUnit.SECONDS.sleep(3);
//        System.err.println("超过断路器再次超过半开时间openTimeout+resetTimeout，断路器半开，断路器方法运行允许三个访问进入");
//        demoApplication.circuitBreaker(7);
//        demoApplication.circuitBreaker(7);
//        demoApplication.circuitBreaker(7);
//        System.err.println("尝试进入断路器方法，在openTimeout时间内，触发异常超过3次，断路器打开，断路器方法不允许执行，直接执行恢复方法");
//        demoApplication.circuitBreaker(8);
//        TimeUnit.SECONDS.sleep(3);
//        System.err.println("超过断路器再次超过半开时间openTimeout+resetTimeout，断路器半开，断路器方法运行允许三个访问进入,并且断路方法不再抛出异常,断路器关闭，方法可持续调用");
//        demoApplication.circuitBreaker(9);
//        demoApplication.circuitBreaker(9);
//        demoApplication.circuitBreaker(9);
//        demoApplication.circuitBreaker(9);
//        demoApplication.circuitBreaker(9);
//        demoApplication.circuitBreaker(9);

//        System.err.println();
//        System.err.println();
//        System.err.println("开始重试");
//        demoApplication.retryable(10);
//        System.err.println("未抛出异常，");
//        demoApplication.retryable(11);
    }

    @CircuitBreaker(maxAttempts=3,openTimeout=1000,resetTimeout=2000,label="test-CircuitBreaker",include=RuntimeException.class,exclude=Exception.class)
    public void circuitBreaker(int num) throws InterruptedException {
        System.err.print(" 进入断路器方法num="+num);
        if(num>8)return;
        Integer n=null;
        System.err.println(1/n);
    }


    @Retryable(label="test-Retryable" , maxAttempts=3,backoff=@Backoff(delay=1),include=RuntimeException.class,exclude=Exception.class)
    public void retryable(int num)  throws InterruptedException {
        System.err.println(" 进入重试方法num="+num);
        if(num>10)return;
        Integer n=null;
        System.err.println(1/n);
    }

    @Recover
    public void recover(NullPointerException exception) {
        System.err.println(" NullPointerException ....");
    }

    @Recover
    public void recover(RuntimeException exception) {
        System.err.println(" RuntimeException ....");
    }

    @Recover
    public void recover(Exception exception) {
        System.err.println(" exception ....");
    }

    @Recover
    public void recover(Throwable throwable) {
        System.err.println(" throwable ....");
    }
    @Recover
    public void recover() {
        System.err.println(" recover ....");
    }
}
