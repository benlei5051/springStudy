package org.andy.bus.kafka.client.event;

import org.springframework.cloud.bus.event.RemoteApplicationEvent;

/**
 *
 * @author: haolin@pateo.com.cn
 * @date: 2018/10/26 11:31
 * @version: V1.0
 */
public class MyCustomRemoteEvent extends RemoteApplicationEvent {
    private String message;

    //    自定义事件时，我们需要添加无参构造方法：目的在于，在message转事件时，会调用这个无参构造方法
    //jackson序列化反序列化必须有无参构造函数
    public MyCustomRemoteEvent() {
        this.message = null;
    }

    public MyCustomRemoteEvent(Object source, String originService, String destinationService, String message) {
        // source is the object that is publishing the event
        // originService is the unique context ID of the publisher

        super(source, originService, destinationService);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
