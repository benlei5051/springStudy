package org.andy.eureka.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author: andy
 * @Date: 2018/3/2 16:26
 * @Description:
 */
@SpringBootApplication
@EnableEurekaClient
public class EurekaClientApplication {
    public static void main(String[] args) {
        String[] args2 = new String[]{"--server.port=8762"};
        SpringApplication.run(EurekaClientApplication.class, args2);
    }
}
