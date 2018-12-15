package org.andy.beans.async.example4;

import org.andy.beans.async.example4.config.TaskThreadPoolConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author: andy
 * @Date: 2017/9/25 14:59
 * @Description:
 */
@SpringBootApplication
@EnableAsync
@EnableConfigurationProperties({TaskThreadPoolConfig.class})
public class Async4DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(Async4DemoApplication.class, args);
    }
}
