package org.andy.eureka.client.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: andy
 * @Date: 2018/3/2 16:28
 * @Description:
 */
@RestController
public class DcController {
    private final Logger logger = LoggerFactory.getLogger(DcController.class);

    @Value("${server.port:8080}")
    private String port;

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/dc")
    public String dc() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String services = "Services: " + discoveryClient.getServices();
        System.out.println(services);
        return services;
    }

    @RequestMapping("/hi")
    public String home(@RequestParam(value = "name", defaultValue = "forezp") String name) {
        logger.info("------------进入eureka-client服务---------------");
        //测试熔断超时
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "hi " + name + " ,i am from port:" + port;
    }
}
