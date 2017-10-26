package org.andy.consumer4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.PropertySource;


@SpringBootApplication(scanBasePackages ={"org.andy.common","org.andy.consumer4"} )
public class Consumer4Application extends SpringBootServletInitializer {

    //  private static final Logger LOGGER = LoggerFactory.getLogger(SpringbootApplication.class);
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        logger.info("进入方法------------------------");
        return application.sources(Consumer4Application.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(Consumer4Application.class, args);
    }
}
