package org.andy.service.feign.util;

import feign.Logger.Level;
import feign.Request;
import feign.Retryer;
import feign.hystrix.HystrixFeign;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import static java.util.concurrent.TimeUnit.SECONDS;

@Configuration
public class FeignClientConfig {

    /**
     * 设置Feign日志级别
     * NONE		不记录 (DEFAULT)
     * BASIC	仅记录请求方式、URL及 响应状态码、执行时间
     * HEADERS	记录日志基本信息与请求头、响应头
     * FULL		记录请求头、响应头、正文及元数据
     */
    @Bean
    public Level feignLoggerLevel() {
        return Level.FULL;
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
    @Scope("prototype")
    Request.Options feignOptions() {
        return new Request.Options(50 * 1000, 50 * 1000);
    }


    /**
     *    你可以重写FeignClientsConfiguration中的bean，从而达到自定义配置的目的，
     *     比如FeignClientsConfiguration的默认重试次数为Retryer.NEVER_RETRY，
     *     即不重试，那么希望做到重写，写个配置文件，注入feignRetryer的bean,代码如下
     *
     * @param
     * @return: feign.Retryer
     * @author: haolin@pateo.com.cn
     * @date: 2018/9/5 11:35
     */
//    该FeignClient的重试次数，重试间隔为100ms，最大重试时间为1s,重试次数为5次。
//    https://blog.csdn.net/forezp/article/details/73480304
    @Bean
    public Retryer feignRetryer() {
        return new Retryer.Default(100, SECONDS.toMillis(1), 5);
    }


}
