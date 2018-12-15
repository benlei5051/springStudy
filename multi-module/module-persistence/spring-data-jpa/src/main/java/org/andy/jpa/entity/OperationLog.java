package org.andy.jpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 * @title 操作日志实体类
 * @describe 操作日志信息
 * @author zc
 * @version 1.0 2017-09-13
 */
@Data
@Entity
public class OperationLog extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String content;

	/**
	 * 字段不可修改
	 *//*
	@Column(name = "created_at", nullable = false, updatable = false)
	private Date createdAt;

	@Column(name = "updated_at", nullable = false)
	private Date updatedAt;


	*//**
	 * 字段自动保存，在save之前被调用
	 *//*
	@PrePersist
	public void onCreate() {
		createdAt = updatedAt = new Date();
	}

	*//**
	 * 字段自动更新，save之后被调用
	 *//*
	@PreUpdate
	public void onUpdate() {
		this.updatedAt = new Date();
	}*/


}
