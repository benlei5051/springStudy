package org.andy.jpa.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author: andy
 * @Date: 2017/11/2 14:17
 * @Description:
 */
@Entity
@DiscriminatorValue("order")
public class OrderMessage extends BaseMessage{

    private Long orderId;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
