package org.andy.beans.condition;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author: andy
 * @Date: 2017/9/25 14:59
 * @Description:
 */
@SpringBootApplication
@Slf4j
public class BeansApplication {

    public static void main(String[] args) {
        String[] args2 = new String[]{"--server.port=8802", "--spring.profiles.active=dev"};
        ConfigurableApplicationContext applicationContext = SpringApplication.run(BeansApplication.class, args2);
        InjectBean bean = null;
        try {
            bean = (InjectBean) applicationContext.getBean("injectBean");
        } catch (BeansException e) {
            log.info("InjectBean is null");
        }
        Optional<InjectBean> optional = Optional.ofNullable(bean);
        //optional是null，则不执行InjectBean中的eat方法
        optional.ifPresent(InjectBean::eat);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            System.out.println("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }

        };
    }

}
