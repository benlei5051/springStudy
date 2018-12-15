package org.andy.bus.kafka.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.bus.jackson.RemoteApplicationEventScan;

/**
 *
 * @author: haolin@pateo.com.cn
 * @date: 2018/10/25 16:41
 * @version: V1.0
 */
@SpringBootApplication
public class Client2Application {

    public static void main(String[] args) {
        String[] args2 = new String[]{"--spring.profiles.active=peer2"};
        SpringApplication.run(Client2Application.class, args2);
    }
}
