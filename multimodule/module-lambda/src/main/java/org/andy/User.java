package org.andy;

import java.util.Optional;

/**
 * @author: andy
 * @Date: 2017/9/13 17:33
 * @Description:
 */
public class User {
    /** 用户编号 */
    private long id;

    private String name;

    private int age;

    private Optional<Long> phone;   //可以为空

    private Optional<String> email;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

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
        return phone;
    }

    public void setPhone(Optional<Long> phone) {
        this.phone = phone;
    }

    public Optional<String> getEmail() {
        return email;
    }

    public void setEmail(Optional<String> email) {
        this.email = email;
    }
}
