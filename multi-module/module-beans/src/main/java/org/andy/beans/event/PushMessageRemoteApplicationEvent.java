package org.andy.beans.event;


import org.springframework.cloud.bus.event.RemoteApplicationEvent;
import org.springframework.util.Assert;


public class PushMessageRemoteApplicationEvent<T extends PushMessage> extends RemoteApplicationEvent {

    private final T message;

 /*   @SuppressWarnings("unused")
    //jackson序列化反序列化必须有无参构造函数
    private PushMessageRemoteApplicationEvent() {
        // for serializers
        message = null;
    }*/
    public PushMessageRemoteApplicationEvent(Object source, String originService,
                                             String destinationService, T message) {
        super(source, originService, destinationService);
        Assert.notNull(message, "Message must not be null");
        this.message = message;
    }

    public T getMessage() {
        return this.message;
    }

}
