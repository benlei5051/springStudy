package org.andy.bus.kafka.client.config;

import org.springframework.cloud.bus.jackson.RemoteApplicationEventScan;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author: haolin@pateo.com.cn
 * @date: 2018/10/31 23:34
 * @version: V1.0
 */
@Configuration
@RemoteApplicationEventScan(basePackages = "org.andy.bus.kafka.client.event")
public class EventScan {
}
