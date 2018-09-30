package org.andy.authority.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.andy.authority.support.BaseEntity;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

/**
 * @author sean
 * 2017/11/2.
 */
@Entity
@Table(name = "opt_role")
@Getter
@Setter
@DynamicUpdate
@Where(clause = "del_flag = 0")
public class Role extends BaseEntity<String> {

    /**
     * 角色名称
     */
    @Column(length = 20, nullable = false)
    @NotNull
    private String name;

    /**
     * 角色简介
     */
    private String remark;

    /**
     * 角色编码
     */
    private String code;

    /**
     * 角色拥有权限的资源集合
     */
    @OneToMany(mappedBy = "role", cascade = {CascadeType.REMOVE})
    @JsonIgnore
    private Set<RoleResource> resources = new HashSet<>();

    /**
     * 角色的用户集合
     */
    @OneToMany(mappedBy = "role", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private Set<RoleUser> users = new HashSet<>();
}
