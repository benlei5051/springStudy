/*------------------------------------------------------------------------------
 * Async2DemoApplication
 * @Description: TODO
 * @author: haolin@pateo.com.cn
 * @date: 2018/11/21 14:02
 * @version V1.0
 * @Copyright 博泰悦臻网络技术服务有限公司
 *---------------------------------------------------------------------------*/
package org.andy.beans.async.example1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class Async1DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(Async1DemoApplication.class, args);
        log.info("Async2DemoApplication 启动!");
    }

}
