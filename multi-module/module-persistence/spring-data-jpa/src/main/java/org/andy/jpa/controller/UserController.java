package org.andy.jpa.controller;

import org.andy.jpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by jh on 2017/8/12.
 */
@Controller
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 通过spring data jpa 调用方法 api :localhost:8080/users/byname/linhao
     *
     */
    @RequestMapping(value = "/byname/{username}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object> getUser(@PathVariable("username") String username) {

        return new ResponseEntity<>(userService.getUserByName(username), HttpStatus.OK);
    }
}
