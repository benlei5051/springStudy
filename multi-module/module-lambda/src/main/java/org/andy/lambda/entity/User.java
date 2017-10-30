package org.andy.lambda.entity;

import java.io.Serializable;
import java.util.Optional;

/**
 * @author: andy
 * @Date: 2017/9/13 17:33
 * @Description:
 */
//Optional是一个final类，未实现任何接口，所以当我们在利用该类包装定义类的属性的时候，
// 如果我们定义的类有序列化的需求，那么因为Optional没有实现Serializable接口，这个时候执行序列化操作就会有问题：
public class User implements Serializable{

    private static final long serialVersionUID = 720727425708251976L;
    /** 用户编号 */
    private long id;

    private String name;

    private int age;

    private Long phone;   //可以为空

    private String email;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Optional<Long> getPhone() {
        return Optional.ofNullable(this.phone);
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public Optional<String> getEmail() {
        return Optional.ofNullable(this.email);
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
