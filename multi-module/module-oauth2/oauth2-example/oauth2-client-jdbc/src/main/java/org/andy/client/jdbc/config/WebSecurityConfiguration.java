package org.andy.client.jdbc.config;

import org.andy.client.jdbc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;

/**
 * @Description: TODO
 * @author: haolin@pateo.com.cn
 * @date: 2018/8/31 17:27
 * @version: V1.0
 */
@Configuration
public class WebSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {

    private final UserService userService;

    @Autowired
    public WebSecurityConfiguration(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);

    }

}
