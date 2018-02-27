package org.andy.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.bus.ConditionalOnBusEnabled;
import org.springframework.cloud.bus.SpringCloudBusClient;
import org.springframework.cloud.bus.jackson.RemoteApplicationEventScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportAware;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.type.AnnotationMetadata;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: joe
 * @Date: 17-8-11 下午9:52.
 * carday 消息总线 复用 springCloudBus
 * 基于 springCloudBus上实现自己的Event,
 * 手动Import
 */
@Configuration
@ConditionalOnBusEnabled
@PropertySource("classpath:/config-kafka.properties")
@RemoteApplicationEventScan("org.andy.kafka.event")

public class BusAutoConfiguration implements ImportAware {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private ConfigurableEnvironment environment;  //动态装配spring配置文件

    private static final String BUS_INPUT_GROUP_CONFIG =
            "spring.cloud.stream.bindings." + SpringCloudBusClient.INPUT + ".group";



    @Override
    public void setImportMetadata(AnnotationMetadata importMetadata) {
        //如果EnableCardayBuS的属性consumerGroup是Class<?>类型，那么
        // Map<String, Object> getAnnotationAttributes(String annotationName, boolean classValuesAsString);
        //如果classValuesAsString为true，可以将consumerGroup对应的类引用，转化为字符串
       /* if (classValuesAsString) {
            if (value instanceof Class) {
                return ((Class<?>) value).getName();
            }
        }*/
        Map<String, Object> cardayBusAttributes = importMetadata.
                getAnnotationAttributes(EnableCardayBus.class.getName(), true);
        if (cardayBusAttributes == null) {
            return;
        }
        // 如果消息总线 开启 Group模式，则设置
        // spring cloud总线的 group
        if ((boolean) cardayBusAttributes.getOrDefault("consumerGroup", false)) {
            MutablePropertySources propertySources = environment.getPropertySources();
            Map<String, Object> properteis = new HashMap<>();
            properteis.put(BUS_INPUT_GROUP_CONFIG, environment.getProperty("spring.application.name", context.getApplicationName()));
            propertySources.addLast(new MapPropertySource("consumer-group", properteis));
        }

    }
    //上面流程大概是实现了ImportAware接口，也就是注解被解析完之后的一个回调，然后通过这个回调拿到具体的参数而已
}
