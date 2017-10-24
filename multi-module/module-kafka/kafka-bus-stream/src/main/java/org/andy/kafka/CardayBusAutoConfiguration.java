package org.andy.kafka;

import org.springframework.cloud.bus.ConditionalOnBusEnabled;
import org.springframework.cloud.bus.jackson.RemoteApplicationEventScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @Author: joe
 * @Date: 17-8-11 下午9:52.
 * carday 消息总线 复用 springCloudBus
 * 基于 springCloudBus上实现自己的Event,
 * 自动加载
 */
@Configuration
@ConditionalOnBusEnabled
@PropertySource("classpath:/config-kafka.properties")
@RemoteApplicationEventScan("org.andy.kafka.event")
public class CardayBusAutoConfiguration {

}
