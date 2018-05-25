package org.andy.swagger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;

import java.util.List;

/**
 * @author: andy
 * @Date: 2017/9/7 14:41
 * @Description:
 */

//http://localhost:8080/swagger-ui.html
@SpringBootApplication
public class SwaggerApplication  extends SpringBootServletInitializer{
    // jar启动
    public static void main(String[] args) {
        SpringApplication.run(SwaggerApplication.class, args);
    }

    // tomcat war启动
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SwaggerApplication.class);
    }
}
