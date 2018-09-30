package org.andy.eureka.consumer;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author: andy
 * @Date: 2018/3/2 17:16
 * @Description:
 */
/*
@EnableDiscoveryClient基于spring-cloud-commons，并且在classpath中实现。

@EnableEurekaClient基于spring-cloud-netflix，只能为eureka作用。

就是如果选用的注册中心是eureka推荐@EnableEurekaClient，如果是其他的注册中心推荐使用@EnableDiscoveryClient，

如果classpath中添加了eureka，则它们的作用是一样的。*/

//@EnableEurekaClient  //启动EnableEureka客户端
@SpringBootApplication
public class EurekaConsumerApplication {

    //ribbon是一个负载均衡客户端，可以很好的控制htt和tcp的一些行为。Feign默认集成了ribbon。

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(EurekaConsumerApplication.class).web(true).run(args);
    }

}
