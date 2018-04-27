package org.andy.authority.handler;


import org.andy.authority.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 登陆失败监听
 *
 * @author Shaoj 3/2/2017.
 */
@Component
@Transactional
public class AuthenticationFailureListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

    @Autowired
    private UserRepository userRepository;


    @Override
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent authenticationFailureBadCredentialsEvent) {
        System.out.println("登录失败1次");
        //在redis中记录登录失败的次数
    }
}