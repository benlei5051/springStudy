package test.andy.service;

import test.andy.dao.UserRepository;
import test.andy.domain.User;
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
     * @param UserName
     * @return
     */
    @Override
    public User getUserByName(String username) {
        return userRepository.findByName(username);
    }
}
