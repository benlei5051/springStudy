package com.andy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * @author: andy
 * @Date: 2017/9/6 14:54
 * @Description:
 */
@SpringBootApplication
public class HibernateValidatorApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        logger.info("进入方法------------------------");
        return application.sources(HibernateValidatorApplication.class);
    }
    public static void main(String[] args) {
        SpringApplication.run(HibernateValidatorApplication.class, args);
    }
}
