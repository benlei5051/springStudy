package org.andy.kafka;

import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * @Author: joe
 * @Date: 17-8-11 下午9:50.
 * @Description:
 */
@Retention(value = java.lang.annotation.RetentionPolicy.RUNTIME)
@Target(value = { java.lang.annotation.ElementType.TYPE })
@Documented
@Import({CardayBusConfiguration.class})
public @interface EnableCardayBus {

    /**
     * 是否 启用消息总线-消费者的 GROUP模式 <br/>
     * GROUP模式下，同一个TOPIC下的一个GROUP中，只会有一个消费者收到消息
     * @return
     */
    boolean consumerGroup() default false;
//    Class<?> consumerGroup();
}
