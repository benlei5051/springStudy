/*------------------------------------------------------------------------------
 * FeeResponseEvent
 * @Description: TODO
 * @author: haolin@pateo.com.cn
 * @date: 2018/12/25 10:03
 * @version V1.0
 * @Copyright 博泰悦臻网络技术服务有限公司
 *---------------------------------------------------------------------------*/
package org.andy.producer2.event;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;

/**
 * 异步通知事件
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class FeeResponseEvent extends ApplicationEvent {

    public FeeResponseEvent(Object source) {
        super(source);
    }
}
