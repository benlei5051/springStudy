package org.andy.beans.schedule;

import org.andy.beans.event.PushMessage;
import org.andy.beans.event.PushTest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author: andy
 * @Date: 2017/9/25 14:59
 * @Description:
 */
@SpringBootApplication
@EnableScheduling
public class BeansApplication{

    public static void main(String[] args) {
        SpringApplication.run(BeansApplication.class, args);
    }
}
