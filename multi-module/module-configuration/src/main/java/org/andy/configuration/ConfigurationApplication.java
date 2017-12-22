package org.andy.configuration;

import org.andy.configuration.bean.ConfigBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;

import java.util.Optional;

/**
 * @author: andy
 * @Date: 2017/12/22 15:49
 * @Description:
 */
@SpringBootApplication
@EnableConfigurationProperties({ConfigBean.class})
public class ConfigurationApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        logger.info("进入方法------------------------");
        return application.sources(ConfigurationApplication.class);
    }
    public static void main(String[] args) {
        ApplicationContext applicationContext= SpringApplication.run(ConfigurationApplication.class, args);
        ConfigBean configBean=(ConfigBean)applicationContext.getBean("configBean");//如果找不到，抛异常
        System.out.println(configBean.getName());

    }
}
