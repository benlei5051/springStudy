/*------------------------------------------------------------------------------
 * BaseEntity
 * @Description: TODO
 * @author: haolin@pateo.com.cn
 * @date: 2018/12/13 15:05
 * @version V1.0
 * @Copyright 博泰悦臻网络技术服务有限公司
 *---------------------------------------------------------------------------*/
package org.andy.jpa.entity;

import cn.hutool.core.date.DateTime;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.ZonedDateTime;
import java.util.Date;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Data
public abstract class BaseEntity {

    /**
     * 创建时间
     */
    @Column(name = "create_time", updatable = false,nullable = false)
    @CreatedDate
    private Date createTime;
    /**
     * 创建人
     */
    @Column(name = "create_by")
    @CreatedBy
    private String createBy;
    /**
     * 修改时间
     */
    @Column(name = "lastmodified_time", nullable = false)
    @LastModifiedDate
    private Date lastmodifiedTime;
    /**
     * 修改人
     */
    @Column(name = "lastmodified_by")
    @LastModifiedBy
    private String lastmodifiedBy;
}
