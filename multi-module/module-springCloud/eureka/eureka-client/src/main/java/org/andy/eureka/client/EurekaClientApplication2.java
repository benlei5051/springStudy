package org.andy.eureka.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author: andy
 * @Date: 2018/3/2 16:26
 * @Description:
 */
@SpringBootApplication
//enableEurekaClient注解可要可不要
@EnableEurekaClient
public class EurekaClientApplication2 {
    public static void main(String[] args) {
        String[] args2 = new String[]{"--server.port=8763"};
        SpringApplication.run( EurekaClientApplication2.class, args2 );
    }
}
