package org.andy.client.memory.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lxg
 * on 2017/2/20.
 */

public class User implements Serializable {
    private int id;
    private String username;
    private String password;
    private List<Role> list;

    public User(int id, String username, String password, List<Role> list) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.list = list;
    }

    public List<Role> getList() {
        return list;
    }

    public void setList(List<Role> list) {
        this.list = list;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
