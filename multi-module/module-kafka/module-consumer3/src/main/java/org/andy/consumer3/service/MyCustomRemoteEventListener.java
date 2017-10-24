package org.andy.consumer3.service;

import org.andy.kafka.event.PushMessageEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author: andy
 * @Date: 2017/10/24 16:20
 * @Description:
 */
@Component
public class MyCustomRemoteEventListener implements ApplicationListener<PushMessageEvent> {
    private  final Logger logger = LoggerFactory.getLogger(MyCustomRemoteEventListener.class);

    @Override
    public void onApplicationEvent(PushMessageEvent event) {
        logger.info("Received MyCustomRemoteEvent - message: " + event.getMessage());
    }
}
