package org.andy.jpa.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author: andy
 * @Date: 2017/11/2 14:00
 * @Description:
 */
@Entity
@DiscriminatorValue("vehicle")
public class VehicleAbnormalMessage extends BaseMessage {

    /**
     * 报警位置
     */
    @Column(name = "location")
    private String location;

    /**
     * 车辆报警Id
     */
    @Column(name = "warning_id")
    private Long warningId;

    /**
     * 车牌号
     */
    @Column(name = "car_no")
    private String carNo;

    /**
     * 车辆所在组织机构Id
     */
    @Column(name = "org_id")
    private Long orgId;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Long getWarningId() {
        return warningId;
    }

    public void setWarningId(Long warningId) {
        this.warningId = warningId;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }
}
