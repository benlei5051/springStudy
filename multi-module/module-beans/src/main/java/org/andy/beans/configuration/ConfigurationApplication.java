package org.andy.beans.configuration;

import lombok.extern.slf4j.Slf4j;

import org.andy.beans.configuration.bean.ConfigBean;
import org.andy.beans.configuration.bean.TrainingProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;

/**
 * @author: andy
 * @Date: 2017/12/22 15:49
 * @Description:
 */
@SpringBootApplication
@EnableConfigurationProperties({ConfigBean.class})
@Slf4j
public class ConfigurationApplication {
    public static void main(String[] args) {
        log.info("---------------");
        ApplicationContext applicationContext= SpringApplication.run(ConfigurationApplication.class, args);
        ConfigBean configBean=(ConfigBean)applicationContext.getBean("configBean");//如果找不到，抛异常
        TrainingProperties trainingProperties=(TrainingProperties)applicationContext.getBean("trainingProperties");//如果找不到，抛异常
        System.out.println(trainingProperties.toString());
        System.out.println(configBean.getName());
    }
}
