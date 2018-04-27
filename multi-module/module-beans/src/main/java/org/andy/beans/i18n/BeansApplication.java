package org.andy.beans.i18n;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.Locale;

/**
 * @author: andy
 * @Date: 2017/9/25 14:59
 * @Description:
 */
@SpringBootApplication
public class BeansApplication {
    @Bean
    public ResourceBundleMessageSource resourceBundleMessageSource() {
        ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
        resourceBundleMessageSource.setBasename("MessageBundle");
        resourceBundleMessageSource.setDefaultEncoding("UTF-8");
        return resourceBundleMessageSource;
    }
    @Bean
    public ReloadableResourceBundleMessageSource resourceBundleMessageSource2() {
        ReloadableResourceBundleMessageSource resourceBundleMessageSource = new ReloadableResourceBundleMessageSource();
        resourceBundleMessageSource.setBasename("classpath:MessageBundle");
        resourceBundleMessageSource.setDefaultEncoding("UTF-8");
        resourceBundleMessageSource.setCacheSeconds(5);
        return resourceBundleMessageSource;
    }

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(BeansApplication.class, args);
        ResourceBundleMessageSource resourceBundleMessageSource = (ResourceBundleMessageSource) applicationContext.getBean("resourceBundleMessageSource");
        String k1 = resourceBundleMessageSource.getMessage("k1", new String[]{"andy"}, Locale.getDefault());
        System.out.println(k1);

//        ReloadableResourceBundleMessageSource resourceBundleMessageSource = (ReloadableResourceBundleMessageSource) applicationContext.getBean("resourceBundleMessageSource2");
//        String k1 = resourceBundleMessageSource.getMessage("k1", new String[]{"andy"}, Locale.getDefault());
//        System.out.println(k1);

    }
}
