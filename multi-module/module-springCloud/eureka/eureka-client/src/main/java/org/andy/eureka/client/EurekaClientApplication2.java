package org.andy.eureka.client;

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
public class EurekaClientApplication2 {
    public static void main(String[] args) {
        String[] args2 = new String[]{"--server.port=2002"};
        new SpringApplicationBuilder(
                EurekaClientApplication2.class)
                .web(true).run(args2);
    }
}
