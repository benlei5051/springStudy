package org.andy.producer2.service;

import org.andy.common.chanel.MyChannel;
import org.andy.producer2.event.FeeResponseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author: andy
 * @Date: 2017/10/24 18:32
 * @Description:
 */
@Component
@EnableBinding(MyChannel.class)
public class SendService implements ApplicationListener<FeeResponseEvent> {

    @Autowired
    @Output(MyChannel.OUTPUT)
    private MessageChannel cloudBusOutboundChannel;

    public void sendMessage(FeeResponseEvent event) {
        try {
            cloudBusOutboundChannel.send(MessageBuilder.withPayload(event.getSource()).build());
            System.out.println("发送成功：" + event.getSource());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 当有发布事件发生时，执行此方法
     * @param feeResponseEvent 费用自定义事件
     */
    @Override
    @Async
    public void onApplicationEvent(FeeResponseEvent feeResponseEvent) {
        this.sendMessage(feeResponseEvent);
    }
}