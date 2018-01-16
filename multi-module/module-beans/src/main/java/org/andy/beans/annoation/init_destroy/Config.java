package org.andy.beans.annoation.init_destroy;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: andy
 * @Date: 2017/9/25 15:01
 * @Description:
 */
@Configuration
public class Config {

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext applicationContext) {

        return args -> {
            System.out.println("Let's inspect the beans provided by Spring Boot:");
            JsrService jsrService=(JsrService) applicationContext.getBean("jsrService");
            jsrService.save("data");
        };
    }
}
