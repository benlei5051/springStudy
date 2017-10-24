package org.andy.consumer2.service;

import org.andy.kafka.bean.PushMessage;
import org.andy.kafka.event.PushMessageEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.bus.event.RemoteApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author: andy
 * @Date: 2017/10/23 10:21
 * @Description:
 */
@Component
public class ConsumerService {

    private  final Logger logger = LoggerFactory.getLogger(ConsumerService.class);

    @EventListener(classes = RemoteApplicationEvent.class)
    public void receiveMessage(RemoteApplicationEvent event) {
        if (event instanceof PushMessageEvent) {
            PushMessage message = ((PushMessageEvent) event).getMessage();

            if (message != null) {
                // 消息入库
                System.out.println(message.getTitle()+"----------------"+message.getContent());
            } else {
               System.out.println("无此消息");
            }
        }
    }
}
