package org.andy.hystrix.feign.service;

import org.springframework.stereotype.Component;

/**
 *
 * @author: haolin@pateo.com.cn
 * @date: 2018/10/12 15:55
 * @version: V1.0
 */
@Component
public class SchedualServiceHiHystric implements SchedualServiceHi {
    @Override
    public String sayHiFromClientOne(String name) {
        return "read time out! " + name;
    }
}