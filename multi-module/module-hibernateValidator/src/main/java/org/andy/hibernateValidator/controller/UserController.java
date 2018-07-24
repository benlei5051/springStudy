package org.andy.hibernateValidator.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.andy.hibernateValidator.domain.UserInfo;
import org.apache.catalina.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import util.AccountLog;

import javax.validation.Valid;
import java.util.Date;

/**
 * @author: andy
 * @Date: 2017/9/6 14:40
 * @Description:
 */
@RestController
@RequestMapping(value = "/users")
public class UserController {
//访问路径   localhost:8082/hibernate-service/services/users/register

//    @JsonView(UserInfo.ProjectDetailView.class)
    @JsonView(UserInfo.ProjectSimpleView.class)
    @AccountLog(operation = "测试切面方法")
    @PostMapping(value = "/register")
    public UserInfo register(@RequestBody @Valid UserInfo userInfo) {
        userInfo.setBirthday(new Date());
        return userInfo;
    }
}
