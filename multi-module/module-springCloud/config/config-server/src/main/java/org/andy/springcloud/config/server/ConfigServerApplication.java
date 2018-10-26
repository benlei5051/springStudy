package org.andy.springcloud.config.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 *
 * 测试是否可以从git中读取数据，请求以下路径
 *
 * http://localhost:8081/test/dev
 *
 *
 *  http请求地址和资源文件映射如下:
 *  /{application}/{profile}[/{label}]
 *  /{application}-{profile}.yml
 *  /{label}/{application}-{profile}.yml
 *  /{application}-{profile}.properties
 *  /{label}/{application}-{profile}.properties
 *
 * @author: haolin@pateo.com.cn
 * @date: 2018/10/17 13:50
 * @version: V1.0
 */
@SpringBootApplication
@EnableConfigServer
public class ConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);
    }
}