package org.andy.authority.support;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.Version;
import java.io.Serializable;
import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;

/**
 * 实体基类
 * @author sean
 * @param <U> 用户ID
 */
@MappedSuperclass
@EqualsAndHashCode(exclude = "id")
@Data
@EntityListeners(AuditingEntityListener.class)
@Where(clause = "del_flag = 0")
public abstract class BaseEntity<U> implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键ID自动生成策略
     */
    @Id
    @GenericGenerator(name = "id", strategy = "uuid2")
    @GeneratedValue(generator = "id")
    @Column(name = "id", length = 36)
    protected String id;


    /**
     * 创建人 登录帐号
     */
    @CreatedBy
    @Column(name = "create_by")
    protected U createdBy;

    /**
     * 修改人 登录的帐号
     */
    @LastModifiedBy
    @Column(name = "update_by",length = 36)
    protected U updateBy;

    /**
     * 创建时间
     */
    @CreatedDate
    @Temporal(TIMESTAMP)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @Column(name = "create_date")
    protected Date createdDate;
    /**
     * 修改时间
     */
    @LastModifiedDate
    @Temporal(TIMESTAMP)
    @Column(name = "update_date")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    protected Date updatedDate;

    /**
     * 删除标志 0 - 正常 1 - 删除
     */
    @Column(name = "del_flag")
    @JsonIgnore
    private Integer delFlag=0;

    /**
     * 版本号
     */
    @Version
    @Column(name = "version")
    @JsonIgnore
    protected Integer version;

}