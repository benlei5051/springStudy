package test.andy.service;

/**
 * Created by jh on 2017/8/12.
 */

import test.andy.domain.User;

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
    public User getUserByName(String username);
}
