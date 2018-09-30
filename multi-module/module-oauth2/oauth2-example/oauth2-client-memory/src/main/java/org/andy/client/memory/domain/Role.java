package org.andy.client.memory.domain;

import java.io.Serializable;

/**
 * Created by lxg
 * on 2017/2/20.
 */

public class Role implements Serializable {
    private int id;
    private String role_name;


    public Role() {
    }

    public Role(int id, String role_name) {
        this.id = id;
        this.role_name = role_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }
}
