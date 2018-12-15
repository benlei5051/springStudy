package org.andy.beans.async.example3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author: andy
 * @Date: 2017/9/25 14:59
 * @Description:
 */
@SpringBootApplication
@EnableAsync
public class Async3DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(Async3DemoApplication.class, args);
    }
}
