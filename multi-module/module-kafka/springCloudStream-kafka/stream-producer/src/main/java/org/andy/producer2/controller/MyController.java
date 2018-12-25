/*------------------------------------------------------------------------------
 * MyController
 * @Description: TODO
 * @author: haolin@pateo.com.cn
 * @date: 2018/12/25 10:45
 * @version V1.0
 * @Copyright 博泰悦臻网络技术服务有限公司
 *---------------------------------------------------------------------------*/
package org.andy.producer2.controller;

import org.andy.producer2.event.FeeResponseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @Autowired
    private ApplicationContext context;

    @RequestMapping(value = "/publish", method = RequestMethod.GET)
    public String publish() {
        final FeeResponseEvent event =
                new FeeResponseEvent("ares");
        context.publishEvent(event);
        return "event published";
    }
}
