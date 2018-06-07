package com.test.website.controller;

import com.test.mysql.entity.Department;
import com.test.mysql.entity.Role;
import com.test.mysql.entity.User;
import com.test.mysql.model.UserQo;
import com.test.mysql.service.DepartmentService;
import com.test.mysql.service.RoleService;
import com.test.mysql.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private RoleService roleService;



    @RequestMapping(value="/{id}")
    public User show(ModelMap model,@PathVariable Long id) {
        User user = userService.findById(id);
        return user;
    }

    @RequestMapping(value = "/list")
    public Page<User> getList(@RequestBody UserQo userQo) {
        try {
            return userService.findPage(userQo);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/new")
    public User create(@RequestBody User user){
        List<Department> departments = departmentService.findAll();
        List<Role> roles = roleService.findAll();
        return user;
    }

    @RequestMapping(value="/save", method = RequestMethod.POST)
    public void save(User user) throws Exception{
        userService.create(user);
        logger.info("新增->ID="+user.getId());
    }

    @RequestMapping(value="/edit/{id}")
    public User update(@PathVariable Long id){
        User user = userService.findById(id);

        List<Department> departments = departmentService.findAll();
        List<Role> roles = roleService.findAll();
        return user;
    }

    @RequestMapping(method = RequestMethod.POST, value="/update")
    @ResponseBody
    public String update(User user) throws Exception{
        userService.update(user);
        logger.info("修改->ID="+user.getId());
        return "1";
    }

    @RequestMapping(value="/delete/{id}",method = RequestMethod.GET)
    @ResponseBody
    public String delete(@PathVariable Long id) throws Exception{
        userService.delete(id);
        logger.info("删除->ID="+id);
        return "1";
    }

}
