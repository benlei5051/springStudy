package org.andy.swagger.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.andy.swagger.entity.Student;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: andy
 * @Date: 2017/9/7 18:01
 * @Description:
 */

@Api(value = "学生信息查询", description = "学生基本信息操作API", tags = "StudentApi", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class StudentController {

    //@ApiOperation(value = "查询学生信息", notes = "依据学生姓名查询学生信息", code = 200, response = Student.class, consumes = MediaType.APPLICATION_JSON_VALUE)
    //@ApiResponse()
    @ApiOperation(value = "getStudent", notes = "依据学生姓名查询学生信息")
    @RequestMapping(value = "student", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Student getStudent(@RequestParam("name") String name) {
        Student reponse = new Student();
        reponse.setId(1);
        reponse.setName("zhangsan");
        reponse.setAge(12);
        reponse.setCls("二年级");
        reponse.setAddress("重庆市大竹林");
        reponse.setSex("男");
        return reponse;
    }

    @ApiOperation(value = "addStudent", notes = "添加一个学生", code = 201)
    @RequestMapping(value = "student", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addStudent(@RequestBody Student student) {
        return;
    }
}

