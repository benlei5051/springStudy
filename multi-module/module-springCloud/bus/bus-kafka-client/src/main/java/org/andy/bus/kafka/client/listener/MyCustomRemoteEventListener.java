package org.andy.bus.kafka.client.listener;

import org.andy.bus.kafka.client.event.MyCustomRemoteEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 *
 * @author: haolin@pateo.com.cn
 * @date: 2018/10/26 11:33
 * @version: V1.0
 */
//自定义事件侦听
@Component
public class MyCustomRemoteEventListener implements ApplicationListener<MyCustomRemoteEvent> {

    private static final Logger logger = LoggerFactory.getLogger(MyCustomRemoteEventListener.class);

    //处理自定义事件
    @Override
    public void onApplicationEvent(MyCustomRemoteEvent myCustomRemoteEvent) {
        System.out.println(myCustomRemoteEvent.getDestinationService()+"---------destin");
        System.out.println(myCustomRemoteEvent.getOriginService()+"----------origin");
        logger.info("Received MyCustomRemoteEvent - message: " + myCustomRemoteEvent.getMessage());
    }
}
