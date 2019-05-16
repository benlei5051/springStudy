/*------------------------------------------------------------------------------
 * BaseEntity
 * @Description: TODO
 * @author: haolin@pateo.com.cn
 * @date: 2018/12/13 15:05
 * @version V1.0
 * @Copyright 博泰悦臻网络技术服务有限公司
 *---------------------------------------------------------------------------*/
package org.andy.jpa.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@MappedSuperclass
@Data
public abstract class BaseEntity2 {

    /**
     * 创建时间
     */
    @Column(name = "create_date", updatable = false, nullable = false)
    protected LocalDateTime createDate;
    /**
     * 创建人
     */
    @Column(name = "create_by", updatable = false)
    protected String createBy;
    /**
     * 修改时间
     */
    @Column(name = "update_date", nullable = false)
    protected LocalDateTime updateDate;
    /**
     * 修改人
     */
    @Column(name = "update_by")
    protected String updateBy;

    /**
     * 备注
     */
    @Column(name = "remarks")
    protected String remarks;

    /**
     * 逻辑删除标志 0 - 未删除，1 - 删除
     */
    @Column(name = "del_flag")
    protected Integer delFlag = 0;


    @PrePersist
    public void onCreate() {
        createDate = updateDate = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        this.updateDate = LocalDateTime.now();
    }
}
