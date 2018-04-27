package org.andy.beans.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * @author: andy
 * @Date: 2017/9/25 14:59
 * @Description:
 */
@SpringBootApplication
@ServletComponentScan
public class BeansApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {


        logger.info("进入方法------------------------");
        return application.sources(BeansApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(BeansApplication.class, args);
    }
}
