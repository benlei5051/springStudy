package org.andy.authority.server;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import sun.security.util.SecurityConstants;

/**
 * @author sean
 * @date 2018/2/23
 */
//@Configuration
//@EnableResourceServer
//public class SecurityResourceServerConfig extends ResourceServerConfigurerAdapter {
//
//
//    @Autowired
//    private JwtAuthenticationEntryPoint unauthorizedHandler;
//
//    @Autowired
//    private FormAuthenticationConfig formAuthenticationConfig;
//
//    @Bean
//    public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
//        return new JwtAuthenticationTokenFilter();
//    }
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        formAuthenticationConfig.configure(http);
//        http.csrf().disable()
//                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
//                .authorizeRequests()
//                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
//                .antMatchers(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL, "/oauth/**", "/optmsurf/active/**",
//                        "/admin/health", "/optmsurf/**/isUse**", "/optmsurf/common/**",
//                        "/optmsurf/account/sendCode", "/optmsurf/user/resetPassword",
//                        "/optmsurf/passwordstrategy/getregular",
//                        "/optmsurf/passwordstrategy/getregularactive").permitAll()
//                .anyRequest().authenticated().and()
//                .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
//
//    }
//}
