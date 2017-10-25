package org.andy.consumer2;

import org.andy.kafka.EnableCardayBus;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;


@SpringBootApplication
@EnableCardayBus(consumerGroup = true)
public class Consumer2Application extends SpringBootServletInitializer {

    //  private static final Logger LOGGER = LoggerFactory.getLogger(SpringbootApplication.class);
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        logger.info("进入方法------------------------");
        return application.sources(Consumer2Application.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(Consumer2Application.class, args);
    }
}
