package design.event;

import java.util.EventListener;

/**
 * @Description: 事件监听器
 * @author: haolin@pateo.com.cn
 * @date: 2018/8/15 17:29
 * @version: V1.0
 */
public class CusEventListener implements EventListener {
    //事件发生后的回调方法
    public void fireCusEvent(CusEvent e) {
        EventSourceObject eObject = (EventSourceObject) e.getSource();
        System.out.println("My name has been changed!");
        System.out.println("I got a new name,named \"" + eObject.getName() + "\"");
    }
}
