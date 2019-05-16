package org.andy.consumer4.service;

import org.andy.common.chanel.MyChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.stereotype.Component;

/**
 * @author: andy
 * @Date: 2017/10/24 18:54
 * @Description:
 */
@Component
@EnableBinding(MyChannel.class)
public class ConsumerService {
    private final Logger logger = LoggerFactory.getLogger(ConsumerService.class);

    @StreamListener(MyChannel.INPUT)
    public void process(String msg) {
        logger.info("received:--------" + msg);
    }
}

