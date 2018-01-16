package org.andy.beans.condition;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: andy
 * @Date: 2017/9/25 17:22
 * @Description:
 */

/**
 * havingValue=true 当配置项reids.mode.pool.enabled的值包含相应的true值，该bean会被spring容器托管
 * matchIfMissing=true:如果该配置项缺失，该bean也会被spirng容器托管
 */
@ConditionalOnProperty(value = InjectEnabled.INJECT_ENABLED, havingValue = "true", matchIfMissing = true)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
public @interface InjectEnabled {
    public static String INJECT_ENABLED = "inject.enabled";
}

