package org.andy.logback.sample2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: andy
 * @Date: 2017/10/27 15:45
 * @Description:
 */
@SpringBootApplication
public class Logback2Application {
    private static final Logger logger = LoggerFactory.getLogger(Logback2Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Logback2Application.class, args);
        logger.trace("======trace");
        logger.debug("======debug");
        logger.info("======info");
        logger.warn("======warn");
        logger.error("======error");
    }
}

