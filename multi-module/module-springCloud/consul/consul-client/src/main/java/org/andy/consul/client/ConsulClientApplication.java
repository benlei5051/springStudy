package org.andy.consul.client;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author: andy
 * @Date: 2018/3/2 16:26
 * @Description:
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ConsulClientApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(
                ConsulClientApplication.class)
                .web(true).run(args);
    }
}
