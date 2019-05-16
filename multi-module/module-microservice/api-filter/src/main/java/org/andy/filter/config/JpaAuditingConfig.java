package org.andy.filter.config;

import org.andy.filter.util.GatewayUtilWrapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Calendar;

/**
 * @author wiiyaya
 * @date 2018/11/29.
 */
@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider", dateTimeProviderRef = "dateTimeProvider")
public class JpaAuditingConfig {

    @Bean
    public AuditorAware<String> auditorProvider() {
        return () -> {
            String authSession = GatewayUtilWrapper.getAuthSessionAdvice().get("projectId");
            if (authSession != null) {
                return "用户登录的Id";
            } else {
                return "mselectronicfence";
            }
        };
    }

    @Bean
    public DateTimeProvider dateTimeProvider() {
        return Calendar::getInstance;
    }
}
