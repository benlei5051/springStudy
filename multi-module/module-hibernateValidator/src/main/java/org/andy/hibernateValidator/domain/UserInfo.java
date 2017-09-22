package org.andy.hibernateValidator.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

public class UserInfo implements Serializable {

    private static final long serialVersionUID = 7561184640219678078L;

    @NotNull(message = "{notEmpty}")
    private Integer id;

    @Size(min = 8, max = 20, message = "{username.size}")
    private String username;

    private Date birthday;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
