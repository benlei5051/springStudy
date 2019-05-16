package org.andy.consumer4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//stream-common 配置了spring.factories,只需要在pom文件中引入module,故不需要在这里特别指定扫描的包名
//@SpringBootApplication(scanBasePackages ={"org.andy.common","org.andy.consumer4"} )
@SpringBootApplication
public class Consumer5Application {


    public static void main(String[] args) {
        String[] args2 = new String[]{"--server.port=9899", "--spring.profiles.active=consumer2"};
        SpringApplication.run(Consumer5Application.class, args2);
    }
}
