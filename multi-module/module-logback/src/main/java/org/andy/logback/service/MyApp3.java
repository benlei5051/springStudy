package org.andy.logback.service;

import org.andy.logback.LogbackApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: andy
 * @Date: 2017/10/27 16:28
 * @Description:
 */
public class MyApp3 {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogbackApplication.class);

    public void logTest() {
        LOGGER.info("Entering application.");
//        Foo foo = new Foo();
//        foo.doIt();
        LOGGER.info("Exiting application.");
    }
}
