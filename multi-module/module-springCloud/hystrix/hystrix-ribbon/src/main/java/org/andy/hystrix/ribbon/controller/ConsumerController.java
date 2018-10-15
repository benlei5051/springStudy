package org.andy.hystrix.ribbon.controller;

import org.andy.hystrix.ribbon.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: andy
 * @Date: 2018/3/2 17:19
 * @Description:
 */


@RestController
public class ConsumerController {

    @Autowired
    HelloService helloService;

    /**
     * 请求路径：http://localhost:8764/hi?name=andy
     * 启动eureka-server,eureka-client,hystrix-ribbon三个微服务
     *
     * @param name
     * @return
     */
    @GetMapping(value = "/hi")
    public String hi(@RequestParam String name) {
        return helloService.hiService(name);
    }


    /**
     * 请求路径：http://localhost:8764/dc
     * @return
     */
    @GetMapping(value = "/dc")
    public String hi() {
        return helloService.testTimeOut();
    }
}
