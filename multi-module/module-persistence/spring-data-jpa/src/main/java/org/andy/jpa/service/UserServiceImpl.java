package org.andy.jpa.service;

import org.andy.jpa.dao.UserRepository;
import org.andy.jpa.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jh on 2017/8/12.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    /**
     *
     * @param username
     * @return
     */
    @Override
    public Users getUserByName(String username) {
        return userRepository.findByName(username);
    }
}
