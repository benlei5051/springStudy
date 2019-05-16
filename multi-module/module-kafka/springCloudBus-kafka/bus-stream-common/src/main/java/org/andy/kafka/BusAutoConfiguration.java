package org.andy.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.bus.ConditionalOnBusEnabled;
import org.springframework.cloud.bus.SpringCloudBusClient;
import org.springframework.cloud.bus.jackson.RemoteApplicationEventScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
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

/*

@Configuration
@ComponentScan(basePackages = "com.cmdt.carday.microservice.common.global",
        excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX,
                pattern = "com\\.cmdt\\.carday\\.microservice\\.common\\.global\\.optional\\..+"))
@PropertySource(value = {"classpath:/postgres.properties",
        "classpath:/environment.properties",
        "classpath:/redis.properties",
        "classpath:/common-service-url.properties"
}, ignoreResourceNotFound = true)

*/

/**
 * 如果项目中引用了一些第三方的类库，如我用到的Redisson库，其内部包含很多@Configuration注解的配置类，
 * 但是我的项目没有自动扫描他的包，那么就可以用@Import(XXX.class)来导入其配置类使其生效。
 * 在Spring4.2以后，@Import还支持导入普通的没有@Configuration注解的类，并将其实例化加入IOC容器中。
 *
 *
 * @import  和 importAware搭配使用，配置类BusAutoConfiguration实现了ImportAware，因此
 * setImportMetadata方法可以通过其importMetadata拿到了@EnableCardayBus注解上的参数
 *
 * 这样就实现了通过注解参数来对配置类BusAutoConfiguration作设置的功能
 */

@Configuration
@ConditionalOnBusEnabled
@PropertySource("classpath:/config-kafka.properties")
// tells spring cloud bus to scan for custom events in the current package and subpackages
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

   // 注意到这个类BusAutoConfiguration注解了@Configuration并实现了ImportAware接口的setImportMetadata方法,
    // 然后通过其metadata拿到了@EEnableCardBus注解上的consumerGroup参数，这样就实现了通过注解参数来对配置类作设置的功能
    // （测试去除@Configuration注解，则ImportAware失效，无法触发setImportMetadata方法的调用）。
}
