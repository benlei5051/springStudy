/**
 *
 */
package org.andy.authority.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * 表单登录配置
 *
 * @author zhailiang
 */
@Component
public class FormAuthenticationConfig {

	@Autowired
	private AuthenticationSuccessHandler authenticationSuccessHandler;

	@Autowired
	private AuthenticationFailureHandler authenticationFailureHandler;

	public void configure(HttpSecurity http) throws Exception {
		http
				.cors()
				.and()
				.formLogin()
				.loginProcessingUrl("/optmsurf/authentication/form")
				.successHandler(authenticationSuccessHandler)
				.failureHandler(authenticationFailureHandler);
	}

}
