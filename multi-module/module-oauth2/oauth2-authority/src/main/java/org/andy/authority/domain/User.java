package org.andy.authority.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.andy.authority.support.BaseEntity;
import org.andy.authority.support.Sex;
import org.andy.authority.support.TimeUtils;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Email;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author sean
 * @date 2017/11/2
 */
@Entity
@Table(name = "opt_user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@Builder
@Where(clause = "del_flag = 0")
public class User extends BaseEntity<String> implements UserDetails {

    /**
     * 用户姓名
     */
    private String realName;

    /**
     * 性别
     */
    private Sex sex;

    /**
     * 嵌入类
     */
    @Embedded
    private Account account = new Account();

    /**
     * 用户的所有角色
     */
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private Set<RoleUser> roles = new HashSet<>();


    /**
     * 项目集合
     */
    @Transient
    @JsonIgnore
    private Set<String> projectCodes;

    /**
     * 将用户的角色和权限code都加载到authorites中
     * 其中角色是ROLE_code
     * 权限是code
     * @return
     */
    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        Set<RoleUser> roleUsers = this.getRoles();
        if (roleUsers != null) {
            for (RoleUser roleUser : roleUsers) {
                SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + roleUser.getRole().getCode());
                authorities.add(authority);
                Set<RoleResource> roleResources = roleUser.getRole().getResources();
                roleResources.forEach(roleResource -> authorities.add(new SimpleGrantedAuthority(roleResource.getResource().getCode())));
            }
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return account.getPassword();
    }

    @Override
    public String getUsername() {
        return account.getUsername();
    }

    /**
     * @return 账户是否过期
     */
    @Override
    public boolean isAccountNonExpired() {
        return account.isActive();
    }

    /**
     * @return 账户是否锁定
     */
    @Override
    public boolean isAccountNonLocked() {
        return !account.isLocked();
    }

    /**
     * @return 密码是否过期(是否激活)
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return TimeUtils.UDateToLocalDate(account.getExpireTime()).isAfter(LocalDate.now());
    }

    /**
     * @return 账户是否删除
     */
    @Override
    public boolean isEnabled() {
        return this.getDelFlag().equals(0) && this.account != null;
    }
}