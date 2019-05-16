package org.andy.swagger.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;

/**
 * @author: andy
 * @Date: 2017/9/7 18:01
 * @Description:
 */
@ApiModel(value = "Student", description = "学生信息描述")
public class Student {

    /**
     * 学号
     */
    @ApiModelProperty("学号")
    private int id;
    /**
     * 姓名
     */
    @ApiModelProperty("姓名")
    private String name;
    /**
     * 年龄
     */
    @ApiModelProperty("年龄")
    private int age;
    /**
     * 性别
     */
    @ApiModelProperty("性别")
    private String sex;
    /**
     * 班级
     */
    @ApiModelProperty("班级")
    private String cls;
    /**
     * 住址
     */
    @ApiModelProperty("家庭住址")
    private String address;


    /**
     * 出生时间
     */
    @ApiModelProperty("出生时间")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime birthDay;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCls() {
        return cls;
    }

    public void setCls(String cls) {
        this.cls = cls;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDateTime getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDateTime birthDay) {
        this.birthDay = birthDay;
    }
}
