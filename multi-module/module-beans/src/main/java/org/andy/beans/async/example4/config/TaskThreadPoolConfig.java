package org.andy.beans.async.example4.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 该注解的locations已经被启用，现在只要是在环境中，都会优先加载
 */
@ConfigurationProperties(prefix = "spring.task.pool")
@Getter
@Setter
public class TaskThreadPoolConfig {

    private int corePoolSize;

    private int maxPoolSize;

    private int keepAliveSeconds;

    private int queueCapacity;
}


