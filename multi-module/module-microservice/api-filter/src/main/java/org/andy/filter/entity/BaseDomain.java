package org.andy.filter.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.io.Serializable;
import java.util.Date;

/**
 * @author wiiyaya
 * @date 2018/11/30.
 */
@Getter
@Setter
@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
public class BaseDomain implements Serializable {
	private static final long serialVersionUID = 8172472448048202546L;

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name= "sid")
	private Long sid;

	/**
	 * 乐观锁版本
	 */
	@Version
	@Column(name= "version")
	private Integer version;

	/**
	 * 删除标记
	 */
	@Column(name= "del_flg")
	private Integer delFlg;

	/**
	 * 创建人
	 */
	@CreatedBy
	@Column(name= "created_by")
	private String createdBy;

	/**
	 * 创建时间
	 */
	@CreatedDate
	@Column(name= "created_dt")
	private Date createdDt;

	/**
	 * 更新人
	 */
	@LastModifiedBy
	@Column(name= "updated_by")
	private String updatedBy;

	/**
	 * 更新时间
	 */
	@LastModifiedDate
	@Column(name= "updated_dt")
	private Date updatedDt;
}
