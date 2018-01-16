package org.andy.beans.async;

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
public class BeansApplication {

    public static void main(String[] args) {
       SpringApplication.run(BeansApplication.class, args);
    }
}
