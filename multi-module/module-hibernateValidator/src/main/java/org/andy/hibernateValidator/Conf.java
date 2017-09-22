package org.andy.hibernateValidator;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * @author: andy
 * @Date: 2017/9/6 16:27
 * @Description:
 */
@Configuration
public class Conf {
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(validator());
    }
    @Bean(name = "DataMsgSource")
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("classpath:message/validateMsg");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setCacheSeconds(5);
        return messageSource;
    }

    @Bean
    public LocalValidatorFactoryBean validator() {
        LocalValidatorFactoryBean ret = new LocalValidatorFactoryBean();
        ret.setValidationMessageSource(messageSource());
        return ret;
    }
}
