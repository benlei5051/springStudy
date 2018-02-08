package org.andy.beans.event;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
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
    }
}
