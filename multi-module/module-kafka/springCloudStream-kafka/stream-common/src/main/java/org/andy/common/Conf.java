package org.andy.common;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author: andy
 * @Date: 2017/10/25 12:38
 * @Description:
 */
@Configuration
@PropertySource(value = "classpath:/kafka.properties")
public class Conf {
}
