package org.andy.eureka.consumer.controller;

import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
    @GetMapping("/consumer")
    public String dc(){
        return restTemplate.getForEntity("http://EUREKA-CLIENT/dc",String.class).getBody();
    }
}
