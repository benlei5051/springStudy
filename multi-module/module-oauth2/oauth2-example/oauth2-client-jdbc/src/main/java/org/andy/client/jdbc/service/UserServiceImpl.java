package org.andy.client.jdbc.service;

import org.andy.client.jdbc.bean.CustomUserDetails;
import org.andy.client.jdbc.bean.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @Description: TODO
 * @author: haolin@pateo.com.cn
 * @date: 2018/8/31 17:30
 * @version: V1.0
 */
@Service
public class UserServiceImpl implements UserService{
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        /*模拟数据库操作*/
        User user = new User();
        user.setUsername("10086");
        user.setPassword("123456");
        return new CustomUserDetails(user);
    }
}
