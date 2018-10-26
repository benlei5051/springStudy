package org.andy.bus.kafka.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.bus.jackson.RemoteApplicationEventScan;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author: haolin@pateo.com.cn
 * @date: 2018/10/25 16:41
 * @version: V1.0
 */
@SpringBootApplication
/**
 * tells spring cloud bus to scan for custom events in the current package and subpackages
 */
@RemoteApplicationEventScan(basePackages = "org.andy.bus.kafka.client.event")

public class ConfigClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigClientApplication.class, args);
    }
}
