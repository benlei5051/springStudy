package org.andy.client.memory.config;

import org.andy.client.memory.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * security配置
 *
 * @author lxg
 *
 * 2017年2月17日上午11:13:55
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class OAuth2SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public MyUserDetailsService myUserDetailsService() {
        return new MyUserDetailsService();
    }

    @Bean
    public Md5PasswordEncoder passwordEncoder() {
        return new Md5PasswordEncoder();
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(myUserDetailsService())
                .passwordEncoder(passwordEncoder());
    }


    /**
     * 这个方法即使不配置，程序运行正确
     *
     * @param http
     * @return: void
     * @author: haolin@pateo.com.cn
     * @date: 2018/8/31 15:17
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .requestMatchers().anyRequest()
                .and()
                .authorizeRequests()
                .antMatchers("/oauth/*").permitAll();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


}
