/*------------------------------------------------------------------------------
 * Status
 * @Description: TODO
 * @author: haolin@pateo.com.cn
 * @date: 2018/12/14 10:12
 * @version V1.0
 * @Copyright 博泰悦臻网络技术服务有限公司
 *---------------------------------------------------------------------------*/
package org.andy.database.lock.example4.entity;

import lombok.Data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name="STATUS")
public class Status implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Column(name="VERSION")
    private Long version;
}
