package org.andy.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EntityScan("org.andy.jpa.entity")
@EnableJpaRepositories(basePackages = {"org.andy.jpa.dao"})
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class SpringbootApplication extends SpringBootServletInitializer {
//      private static final Logger LOGGER = LoggerFactory.getLogger(SpringbootApplication.class);
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        logger.info("进入方法------------------------");
        return application.sources(SpringbootApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    }
}
