package org.andy.producer;

import org.andy.kafka.EnableCardayBus;
import org.andy.kafka.bean.PushMessage;
import org.andy.producer.service.ProducerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class ProducerApplication extends SpringBootServletInitializer {

    //  private static final Logger LOGGER = LoggerFactory.getLogger(SpringbootApplication.class);
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        logger.info("进入方法------------------------");
        return application.sources(ProducerApplication.class);
    }

    public static void main(String[] args) {
        ApplicationContext context=SpringApplication.run(ProducerApplication.class, args);
        ProducerService producerService = (ProducerService) context.getBean("producerService");
        producerService.testEvent();
    }
}
