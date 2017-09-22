package org.andy.hibernateValidator.controller;

import org.andy.hibernateValidator.domain.UserInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author: andy
 * @Date: 2017/9/6 14:40
 * @Description:
 */
@RestController
@RequestMapping(value = "/users")
public class UserController {

    @PostMapping(value = "/register")
    public String register(@RequestBody @Valid UserInfo userInfo) {
        return "成功进入";
    }

}
