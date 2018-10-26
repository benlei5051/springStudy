package org.andy.bus.kafka.client.controller;

import org.andy.bus.kafka.client.event.MyCustomRemoteEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author: haolin@pateo.com.cn
 * @date: 2018/10/26 11:32
 * @version: V1.0
 */

@RestController
@RefreshScope //允许动态刷新配置
public class MyController {

    //注入ApplicationContext,通过ApplicationContext来publish remote event
    private ApplicationContext context;

    @Autowired
    public MyController(ApplicationContext context) {
        this.context = context;
    }

    @Value("${foo}")
    private String from;

    @Autowired
    private Environment env;

//http://localhost:7001/from
    @RequestMapping("/from")
    public String from() {
        return this.from;
    }

    @RequestMapping("/from-env")
    public String fromEnv() {
        //也可以通过env来获取
        return env.getProperty("foo", "undefined");
    }

    @RequestMapping(value = "/publish", method = RequestMethod.POST)
    public String publish(@RequestParam(value = "destination", required = false, defaultValue = "**") String destination) {
        // each service instance must have a unique context ID

        final String myUniqueId = context.getId();

        final MyCustomRemoteEvent event =
                new MyCustomRemoteEvent(this, myUniqueId, destination, "hello world");
        //Since we extended RemoteApplicationEvent and we've configured the scanning of remote events using @RemoteApplicationEventScan, it will be treated as a bus event rather than just a regular ApplicationEvent published in the context.
        //因为我们在启动类上设置了@RemoteApplicationEventScan注解，所以通过context发送的时间将变成一个bus event总线事件，而不是在自身context中发布的一个ApplicationEvent
        context.publishEvent(event);

        return "event published";
    }

}
