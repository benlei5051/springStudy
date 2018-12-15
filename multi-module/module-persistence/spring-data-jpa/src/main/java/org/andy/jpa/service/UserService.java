package org.andy.jpa.service;

/**
 * Created by jh on 2017/8/12.
 */

import org.andy.jpa.entity.Users;

/**
 * The Interface UserService.
 */
public interface UserService {

    /**
     * Gets the user by name.
     *
     * @param username the user name
     * @return the user by name
     */
    public Users getUserByName(String username);
}
