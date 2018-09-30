package org.andy.client.jdbc.bean;

import java.util.Collections;

/**
 * @Description: TODO
 * @author: haolin@pateo.com.cn
 * @date: 2018/8/31 17:31
 * @version: V1.0
 */
public class CustomUserDetails extends org.springframework.security.core.userdetails.User {

    private User user;

    public CustomUserDetails(User user) {
        super(user.getUsername(), user.getPassword(), true, true, true, true, Collections.EMPTY_SET);
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
