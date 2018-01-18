package org.andy.redis.message.receive;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.CountDownLatch;

/**
 * @author: andy
 * @Date: 2017/9/7 10:58
 * @Description:
 */
@Slf4j
public class Receiver {

    private CountDownLatch latch;

    @Autowired
    public Receiver(CountDownLatch latch) {
        this.latch = latch;
    }

    public void receiveMessage(String message) {
        log.info("Received <" + message + ">");
        latch.countDown();
    }
}
