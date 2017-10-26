package org.andy.consumer;

import org.andy.kafka.EnableCardayBus;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;


@SpringBootApplication
@EnableCardayBus(consumerGroup = true)
public class ConsumerApplication extends SpringBootServletInitializer {

    //  private static final Logger LOGGER = LoggerFactory.getLogger(SpringbootApplication.class);
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        logger.info("进入方法------------------------");
        return application.sources(ConsumerApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }
}
