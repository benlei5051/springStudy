package org.andy.authority.service;

import lombok.extern.slf4j.Slf4j;
import org.andy.authority.domain.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author sean
 * @date 2017/11/2
 */
@Slf4j
@Component
public class MyUserDetailsService implements UserDetailsService {

    @Resource
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("表单登录用户名:" + username);
        User user = userService.findUserByLoginName(username);
        if (user == null) {
            throw new UsernameNotFoundException("帐户名为" + username + "的用户不存在");
        }
        return user;
    }
}
