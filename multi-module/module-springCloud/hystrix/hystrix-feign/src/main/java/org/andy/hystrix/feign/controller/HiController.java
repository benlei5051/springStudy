package org.andy.hystrix.feign.controller;

import org.andy.hystrix.feign.service.SchedualServiceHi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author: haolin@pateo.com.cn
 * @date: 2018/10/12 15:49
 * @version: V1.0
 */
@RestController
public class HiController {
    @Autowired
    SchedualServiceHi schedualServiceHi;

    /**
     * 访问路径: http://localhost:8765/hi?name=andy
     *
     * @param name
     * @return
     */
    @GetMapping(value = "/hi")
    public String sayHi(@RequestParam String name) {
        return schedualServiceHi.sayHiFromClientOne(name);
    }
}

