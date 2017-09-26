package org.andy.beans;

import org.andy.beans.condition.InjectBean;
import org.andy.beans.condition.PoolOrClusterEnabled;
import org.andy.beans.service.JsrService;
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
            jsrService.destory();
        };
    }
    @Bean
    @PoolOrClusterEnabled
    public InjectBean injectBean(){
        return new InjectBean();
    }

}
