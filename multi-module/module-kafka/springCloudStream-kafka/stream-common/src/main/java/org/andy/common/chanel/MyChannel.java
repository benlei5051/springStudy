package org.andy.common.chanel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author: andy
 * @Date: 2017/10/24 18:23
 * @Description:
 */
public interface MyChannel {
    String INPUT = "myInput";

    String OUTPUT = "myOutput";

    @Output(MyChannel.OUTPUT)
    MessageChannel myOutput();

    @Input(MyChannel.INPUT)
    SubscribableChannel myInput();
}
