package org.andy.beans.annoation.init_destroy;

import org.andy.beans.event.PushMessage;
import org.andy.beans.event.PushTest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author: andy
 * @Date: 2017/9/25 14:59
 * @Description:
 */
@SpringBootApplication
public class BeansApplication{

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(BeansApplication.class, args);
        run.close();
    }
}
