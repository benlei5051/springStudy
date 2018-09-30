package org.andy.client.memory.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.InMemoryClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    //直接注入一个AuthenticationManager，自动开启密码授权类型
    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;


    @Bean
    public TokenStore tokenStore() {
        return new RedisTokenStore(redisConnectionFactory);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore())
                .authenticationManager(authenticationManager)
                .tokenServices(defaultTokenServices());
    }

    /**
     * <p>注意，自定义TokenServices的时候，需要设置@Primary，否则报错，</p>
     * @return
     */
    @Primary
    @Bean
    public DefaultTokenServices defaultTokenServices(){
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(tokenStore());
        tokenServices.setSupportRefreshToken(true);
//        tokenServices.setClientDetailsService(clientDetails());
        tokenServices.setAccessTokenValiditySeconds(60); // token有效期自定义设置，默认12小时
        tokenServices.setRefreshTokenValiditySeconds(60 * 60);//默认30天，这里修改
        return tokenServices;
    }


    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.checkTokenAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients();
    }


    /**
     * 客户端配置
     *
     * 可以配置内存中，生产环境中配置在数据库中
     *
     * @param clients
     * @return: void
     * @author: haolin@pateo.com.cn
     * @date: 2018/8/30 14:59
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("client")//客户端ID
                .secret("123456")
                .authorizedGrantTypes("password", "refresh_token")//设置验证方式
                .scopes("read", "write");
//                .accessTokenValiditySeconds(10000) //token过期时间
//                .refreshTokenValiditySeconds(10000);
    }
//    @Bean
//    public ClientDetailsService clientDetails() {
//        return new InMemoryClientDetailsService();
//    }
}
