package org.andy.kafka.event;


import org.andy.kafka.bean.PushMessage;
import org.springframework.cloud.bus.event.RemoteApplicationEvent;

/**
 * @Author: joe
 * @Date: 17-8-21 上午11:20.
 * @Description:
 */
public class PushMessageEvent extends RemoteApplicationEvent {

    private final PushMessage message;

    @SuppressWarnings("unused")
    private PushMessageEvent() {
        // for serializers
        message = null;
    }
    public PushMessageEvent(Object source, String originService,
                            String destinationService, PushMessage message) {
        super(source, originService, destinationService);
        this.message = message;
    }

    public PushMessage getMessage() {
        return message;
    }

}
