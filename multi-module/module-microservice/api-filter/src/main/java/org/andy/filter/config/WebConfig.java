package org.andy.filter.config;

import org.andy.filter.global.GlobalExceptionHandler;
import org.andy.filter.global.RestResultAdvice;
import org.hibernate.validator.HibernateValidator;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.data.web.SortHandlerMethodArgumentResolver;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * @author wiiyaya
 * @date 2018/11/29.
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Bean
    public RestResultAdvice restResultAdvice() {
        return new RestResultAdvice();
    }

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        MethodValidationPostProcessor methodValidationPostProcessor = new MethodValidationPostProcessor();
        methodValidationPostProcessor.setValidatorFactory(localValidatorFactoryBean());
        return methodValidationPostProcessor;
    }

    @Bean
    public LocalValidatorFactoryBean localValidatorFactoryBean() {
        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        validator.setProviderClass(HibernateValidator.class);
        return validator;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new GatewayInfoFilter());
        return registration;
    }

    @Override
    public Validator getValidator() {
        return localValidatorFactoryBean();
    }

   /* @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        PageableHandlerMethodArgumentResolver pageableResolver = new PageableHandlerMethodArgumentResolver(new SortHandlerMethodArgumentResolver());
        pageableResolver.setFallbackPageable(new PageRequest(0, 10));
        pageableResolver.setMaxPageSize(100);
        pageableResolver.setOneIndexedParameters(true);
        argumentResolvers.add(pageableResolver);
    }*/
}
