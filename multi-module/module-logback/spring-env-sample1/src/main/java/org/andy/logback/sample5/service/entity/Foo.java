package org.andy.logback.sample5.service.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    /**
     * 标注了 @PostConstruct 注释的方法将在类实例化后调用，
     * 而标注了 @PreDestroy 的方法将在类销毁之前调用。
     */
    @PostConstruct
    public void doIt() {
        logger.trace("Did it again!------trace");
        logger.debug("Did it again!------debug");
        logger.warn("Did it again!-------warn");
    }
}

