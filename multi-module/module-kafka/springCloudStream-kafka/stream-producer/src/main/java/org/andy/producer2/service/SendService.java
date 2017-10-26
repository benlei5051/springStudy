package org.andy.producer2.service;

import org.andy.common.chanel.MyChannel;
import org.andy.common.entity.MessageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;

/**
 * @author: andy
 * @Date: 2017/10/24 18:32
 * @Description:
 */
@EnableBinding(Source.class)
public class SendService {

    @Autowired
    private  Source source;

    public void sendMessage(String msg) {
        try {
            source.output().send(MessageBuilder.withPayload(msg).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}