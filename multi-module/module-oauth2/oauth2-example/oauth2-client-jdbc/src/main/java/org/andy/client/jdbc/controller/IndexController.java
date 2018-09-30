package org.andy.client.jdbc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: TODO
 * @author: haolin@pateo.com.cn
 * @date: 2018/8/31 17:32
 * @version: V1.0
 */
@RestController
public class IndexController {

    @GetMapping("/sayHello")
    private String sayHello(){
        System.out.println("Hello World");
        return "Hello World";
    }

}
