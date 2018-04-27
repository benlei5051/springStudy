/**
 *
 */
package org.andy.authority.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 角色用户关系表
 *
 * @author sean
 *
 */
@Entity
@Table(name = "opt_role_user")
@Getter
@Setter
public class RoleUser implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GenericGenerator(name = "id", strategy = "uuid2")
	@GeneratedValue(generator = "id")
	private String id;
	/**
	 * 角色
	 */
	@ManyToOne
	private Role role;
	/**
	 * 账户
	 */
	@ManyToOne
	private User user;

}
