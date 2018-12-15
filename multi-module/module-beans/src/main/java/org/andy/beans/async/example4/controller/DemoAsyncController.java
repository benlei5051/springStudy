package org.andy.beans.async.example4.controller;


import lombok.extern.slf4j.Slf4j;
import org.andy.beans.async.example4.service.DemoAsyncService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping(value = "/async")
public class DemoAsyncController {

    @Resource
    private DemoAsyncService demoAsyncService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public void demoTest() {
        for (int i = 0; i < 5; i++) {
            demoAsyncService.doTaskFour(i);
        }
        log.info("All tasks finished.");
    }
}
