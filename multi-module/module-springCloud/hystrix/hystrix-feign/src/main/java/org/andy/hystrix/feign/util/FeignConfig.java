package org.andy.hystrix.feign.util;

import feign.Logger;
import feign.Request;
import feign.Retryer;
import feign.hystrix.HystrixFeign;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 *
 * application文件的优先级高于配置类
 *
 * @author: haolin@pateo.com.cn
 * @date: 2018/10/12 16:34
 * @version: V1.0
 */
@Configuration
public class FeignConfig {
    /**
     * 请求连接的超时时间
     */
    public static int connectTimeOutMillis = 18000;

    /**
     * 请求处理的超时时间
     */
    public static int readTimeOutMillis = 18000;

    /**
     *
     * feign默认是不开启重试，如果要开启需配置new Retryer.Default(100, SECONDS.toMillis(1), 5)
     *
     * feign默认重试次数是5次，但不是默认开启重试
     *
     * 更改了feignClient的重试次数，重试间隔为100ms，最大重试时间为1s,重试次数为5次
     * TimeUnit.SECONDS.toSeconds(1) 将1秒转为秒
     * TimeUnit.SECONDS.toMinutes(60) 将60秒转为分钟
     *
     * @return
     */
    @Bean
    public Retryer feignRetryer() {
        return new Retryer.Default(100, SECONDS.toMillis(1), 5);
    }

    /**
     * 设置Feign日志级别
     * NONE		不记录 (DEFAULT)
     * BASIC	仅记录请求方式、URL及 响应状态码、执行时间
     * HEADERS	记录日志基本信息与请求头、响应头
     * FULL		记录请求头、响应头、正文及元数据
     */
    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    /**
     * 开启decode404，处理Rest规范Api找不到资源返回404问题，同时也避免熔断误判。
     *
     * 对于Spring data rest风格findOne类型的接口，都没有状态码返回，只返回一个资源对象；
     * 针对此类情况，不能Feign client fallback，必须通过捕获异常处理。
     */
    @Bean
    @Scope("prototype")
    public HystrixFeign.Builder feignBuilder() {
        return HystrixFeign.builder().decode404();
    }


    @Bean
    public Request.Options options() {
        /*这个配置的优先级低于applicaiton文件中超时的配置*/
        return new Request.Options(connectTimeOutMillis, readTimeOutMillis);
    }

}
