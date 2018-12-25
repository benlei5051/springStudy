package org.andy.producer2;

import org.andy.producer2.event.FeeResponseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(scanBasePackages = {"org.andy.common", "org.andy.producer2"})
@EnableAsync
public class SourceApplication {


    public static void main(String[] args) {

//		String[] args2 = new String[]{"--server.port=8800","--spring.profiles.active=aout"};
        SpringApplication.run(SourceApplication.class, args);

    }

}
