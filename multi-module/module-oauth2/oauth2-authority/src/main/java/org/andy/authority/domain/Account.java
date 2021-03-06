package org.andy.authority.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;

/**
 * @author sean
 * 2017/11/2.
 */

@Embeddable
@Getter
@Setter
public class Account {
    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    @JsonIgnore
    private String password;


    /**
     * 锁定
     */
    private boolean locked;

    /**
     * 激活
     */
    private boolean active;

    /**
     * 过期时间
     */
    @Temporal(TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date expireTime = new Date();

    /**
     * 账户锁定次数，最多5次进入锁定
     */
    private int accountLockCount = 0;

    /**
     * 创建方式:0手动，1自动勾选，导入
     */
    private boolean leadIn;

    /**
     * 过期密码
     */
    @JsonIgnore
    private String oldPasswords;
}
