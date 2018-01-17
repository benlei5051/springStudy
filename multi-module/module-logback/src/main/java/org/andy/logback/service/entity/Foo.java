package org.andy.logback.service.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author: andy
 * @Date: 2017/10/27 16:31
 * @Description:
 */
@Component
public class Foo {
    private static final Logger logger = LoggerFactory.getLogger(Foo.class);

    @PostConstruct
    public void doIt() {
        logger.trace("Did it again!------trace");
        logger.debug("Did it again!------debug");
        logger.warn("Did it again!-------warn");
    }
}

