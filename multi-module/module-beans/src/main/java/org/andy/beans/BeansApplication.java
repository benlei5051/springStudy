package org.andy.beans;

import org.andy.beans.condition.InjectBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.Optional;

/**
 * @author: andy
 * @Date: 2017/9/25 14:59
 * @Description:
 */
@SpringBootApplication
@EnableAsync
public class BeansApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        logger.info("进入方法------------------------");
        return application.sources(BeansApplication.class);
    }
    public static void main(String[] args) {
        ApplicationContext applicationContext=SpringApplication.run(BeansApplication.class, args);
       /* InjectBean bean=(InjectBean)applicationContext.getBean("injectBean");//如果找不到，抛异常
        Optional<InjectBean> optional=Optional.ofNullable(bean);
        optional.ifPresent(InjectBean::eat);*/
    }
}
