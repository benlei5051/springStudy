package org.andy.producer2.service;

import org.andy.common.chanel.MyChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;

/**
 * @author: andy
 * @Date: 2017/10/24 18:32
 * @Description:
 */
@EnableBinding(MyChannel.class)
public class SendService {

    @Autowired
    private MyChannel myChannel;

    public void sendMessage(String msg) {
        try {
            myChannel.myOutput().send(MessageBuilder.withPayload(msg).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}