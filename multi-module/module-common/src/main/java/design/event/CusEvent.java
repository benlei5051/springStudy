package design.event;

import java.util.EventObject;

//java 事件机制包括三个部分：事件、事件监听器、事件源。

/**
 * @Description: 事件
 * @author: haolin@pateo.com.cn
 * @date: 2018/8/15 17:27
 * @version: V1.0
 */
public class CusEvent extends EventObject {

    private static final long serialVersionUID = 1L;

    private Object source;//事件源
    /**
     * Constructs a prototypical Event.
     *
     * @param    source    The object on which the Event initially occurred.
     * @exception IllegalArgumentException  if source is null.
     */
    public CusEvent(Object source) {
        super(source);
        this.source = source;
    }

    @Override
    public Object getSource() {
        return source;
    }

    public void setSource(Object source) {
        this.source = source;
    }
}
