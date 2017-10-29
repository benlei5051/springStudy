package org.andy.logback.service.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: andy
 * @Date: 2017/10/27 16:31
 * @Description:
 */
public class Foo {
    static final Logger logger = LoggerFactory.getLogger(Foo.class);

    public void doIt() {
        logger.debug("Did it again!");
    }
}

