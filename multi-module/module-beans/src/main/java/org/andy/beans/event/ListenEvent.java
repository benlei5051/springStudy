package org.andy.beans.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.PayloadApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author: andy
 * @Date: 2018/1/15 15:39
 * @Description:在同一个JVM中基于event事件编程，如果要在不同的JVM中实现event编程，需依赖springcloudbus，用kafka作为中间件
 */

@Component
public class ListenEvent {

    @EventListener(value = PayloadApplicationEvent.class)
    public void test1(PayloadApplicationEvent payloadEvent) {

        if (payloadEvent.getPayload() instanceof  PushMessage) {
            System.out.println("test1:" + ((PushMessage)payloadEvent.getPayload()).getTitle() + ",text:" + ((PushMessage)payloadEvent.getPayload()).getContent());
        }
    }
    @EventListener(value = PayloadApplicationEvent.class)
    public void test2(PayloadApplicationEvent payloadEvent) {
        if (payloadEvent.getPayload() instanceof  PushTest){
            System.out.println("test2:" + ((PushTest)payloadEvent.getPayload()).getTitle() + ",text:" + ((PushTest)payloadEvent.getPayload()).getContent());
        }
    }
}
