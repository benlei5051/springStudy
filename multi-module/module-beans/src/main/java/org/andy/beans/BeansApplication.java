package org.andy.beans;

import org.andy.beans.event.PushMessage;
import org.andy.beans.event.PushTest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author: andy
 * @Date: 2017/9/25 14:59
 * @Description:
 */
@SpringBootApplication
//@EnableAsync
public class BeansApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        logger.info("进入方法------------------------");
        return application.sources(BeansApplication.class);
    }
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(BeansApplication.class, args);

        run.publishEvent(new PushMessage("test1","aaaaaaaaaa"));
        run.publishEvent(new PushTest("tes2","bbbbbbbbbbbbb"));
        run.close();
       /* InjectBean bean=(InjectBean)applicationContext.getBean("injectBean");//如果找不到，抛异常
        Optional<InjectBean> optional=Optional.ofNullable(bean);
        optional.ifPresent(InjectBean::eat);*/
    }
}
