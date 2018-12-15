package org.andy.file.repository;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public class CommonEntity implements Serializable {
	private static final long serialVersionUID = -6980898251893461542L;

	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@Column(name = "sid", length = 32, nullable = false, unique = true)
	private String sid;

	/**
	 * 备注
	 */
	@Column(name = "remarks", length = 128)
	protected String remarks;

	/**
	 * 删除标志 0 - 正常，1 - 删除
	 */
	@Column(name = "del_flag", length = 2)
	protected Integer delFlag = 0;

	/**
	 * 版本号
	 */
	@Column(name = "version", length = 10)
	protected Integer version = 1;

	/**
	 * 创建人 登录帐号
	 */
	@Column(name = "create_by", length = 50)
	protected String createBy = "sys";

	/**
	 * 创建时间
	 */
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	protected Date createDate = new Date();

	/**
	 * 修改人 登录帐号
	 */
	@Column(name = "update_by", length = 50)
	protected String updateBy = "sys";

	/**
	 * 修改时间
	 */
	@Column(name = "update_date")
	@Temporal(TemporalType.TIMESTAMP)
	protected Date updateDate = new Date();

}
