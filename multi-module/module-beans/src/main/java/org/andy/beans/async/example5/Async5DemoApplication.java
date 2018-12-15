/*------------------------------------------------------------------------------
 * Async2DemoApplication
 * @Description: TODO
 * @author: haolin@pateo.com.cn
 * @date: 2018/11/21 14:02
 * @version V1.0
 * @Copyright 博泰悦臻网络技术服务有限公司
 *---------------------------------------------------------------------------*/
package org.andy.beans.async.example5;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@Slf4j
@EnableAsync
public class Async5DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(Async5DemoApplication.class, args);
        log.info("Async5DemoApplication 启动!");
    }

}
