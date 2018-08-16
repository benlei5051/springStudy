package design.event;

import design.event.CusEvent;
import design.event.CusEventListener;
import design.event.EventSourceObject;

/**
 * @Description: TODO
 * @author: haolin@pateo.com.cn
 * @date: 2018/8/15 17:34
 * @version: V1.0
 */
public class MainTest {
    public static void main(String[] args) {
        EventSourceObject object = new EventSourceObject();
        //注册监听器
        object.addCusListener(new CusEventListener() {
            @Override
            public void fireCusEvent(CusEvent e) {
                super.fireCusEvent(e);
            }
        });
        //触发事件
        object.setName("andy");

    }
}
