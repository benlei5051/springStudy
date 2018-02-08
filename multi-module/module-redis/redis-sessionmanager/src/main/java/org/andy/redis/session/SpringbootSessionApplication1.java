package org.andy.redis.session;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author: andy
 * @Date: 2018/2/7 15:05
 * @Description:
 */
@Slf4j
@SpringBootApplication
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 60)
public class SpringbootSessionApplication1 {

    public static void main(String[] args){
        String[] args2 = new String[]{"--server.port=8081"};
        SpringApplication.run(SpringbootSessionApplication1.class, args2);
    }
}
