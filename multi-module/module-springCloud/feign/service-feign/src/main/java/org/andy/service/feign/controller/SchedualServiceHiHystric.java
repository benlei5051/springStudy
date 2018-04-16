package org.andy.service.feign.controller;

import org.springframework.stereotype.Component;

/**
 * @author: andy
 * @Date: 2018/4/11 15:37
 * @Description:
 */
@Component
public class SchedualServiceHiHystric implements SchedualServiceHi {
    @Override
    public String sayHiFromClientOne(String name) {
        return "sorry "+name;
    }
}
