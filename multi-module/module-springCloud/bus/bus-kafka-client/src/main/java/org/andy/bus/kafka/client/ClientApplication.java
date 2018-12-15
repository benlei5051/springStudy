package org.andy.bus.kafka.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @author: haolin@pateo.com.cn
 * @date: 2018/10/25 16:41
 * @version: V1.0
 */
@SpringBootApplication
public class ClientApplication {

    public static void main(String[] args) {
        String[] args2 = new String[]{"--spring.profiles.active=peer1"};
        SpringApplication.run(ClientApplication.class, args2);
    }
}
