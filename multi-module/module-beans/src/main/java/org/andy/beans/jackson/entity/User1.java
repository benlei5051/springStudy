package org.andy.beans.jackson.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.Date;

/**
 * @author: andy
 * @Date: 2017/12/27 15:54
 * @Description:
 *
 * 注解:@jsonIgnore @jsonFormat @jsonProperty @JsonIgnoreProperties的使用
 *
 * 属性重命名时使用的注解 @jsonProperty
 *
 */
//@JsonIgnoreProperties(value = {"my_email"})
//@JsonIgnoreProperties(value = {"name", "age"},ignoreUnknown = true)
//@JsonInclude(JsonInclude.Include.NON_NULL)
    //自动
@JsonAutoDetect(fieldVisibility=JsonAutoDetect.Visibility.NONE,getterVisibility =JsonAutoDetect.Visibility.NONE )
public class User1 {
    private String name;

    //不JSON序列化年龄属性
   // @JsonIgnore
    private Integer age;

    //格式化日期属性
  //  @JsonFormat(pattern = "yyyy年MM月dd日")
    private Date birthday;

    //序列化email属性为mail
   // @JsonIgnore
  //  @JsonProperty("my_email")
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                ", email='" + email + '\'' +
                '}';
    }
}
