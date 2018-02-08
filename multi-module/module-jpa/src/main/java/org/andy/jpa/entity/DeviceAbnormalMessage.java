package org.andy.jpa.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author: andy
 * @Date: 2017/11/2 14:36
 * @Description:
 */
@Entity
@DiscriminatorValue("device")
public class DeviceAbnormalMessage extends BaseMessage {
}
