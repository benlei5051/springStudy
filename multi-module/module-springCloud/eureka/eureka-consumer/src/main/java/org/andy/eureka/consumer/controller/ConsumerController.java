package org.andy.eureka.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author: andy
 * @Date: 2018/3/2 17:19
 * @Description:
 */


@RestController
public class ConsumerController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/hi")
    public String dc(@RequestParam String name) {
        return restTemplate.getForEntity("http://SERVICE-HI/hi?name=" + name, String.class).getBody();
    }
}
