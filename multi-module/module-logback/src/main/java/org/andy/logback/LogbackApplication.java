package org.andy.logback;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;
import org.andy.logback.service.entity.Foo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author: andy
 * @Date: 2017/10/27 15:45
 * @Description:
 */
@SpringBootApplication
@EnableScheduling
public class LogbackApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogbackApplication.class);
    public static void main(String[] args) {
        ApplicationContext applicationContext=SpringApplication.run(LogbackApplication.class, args);
        LOGGER.debug("debug1111------------------------");
        Foo foo=(Foo)applicationContext.getBean("foo");
        foo.doIt();
        LOGGER.info("info2222------------------------");
    }
}

