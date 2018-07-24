package org.andy.hibernateValidator.domain;

import com.fasterxml.jackson.annotation.JsonView;
import org.andy.hibernateValidator.constraint.annoation.CheckMessageType;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

public class UserInfo implements Serializable {

    public interface ProjectSimpleView {}

    public interface ProjectDetailView extends ProjectSimpleView {}

    private static final long serialVersionUID = 7561184640219678078L;

    @NotNull(message = "{notEmpty}")
    @JsonView(ProjectSimpleView.class)
    private Integer id;

    @Size(min = 8, max = 20, message = "{username.size}")
    @JsonView(ProjectSimpleView.class)
    private String username;

    @JsonView(ProjectDetailView.class)
    private Date birthday;

    @CheckMessageType
//    @JsonView(ProjectSimpleView.class)
    private String message;

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
