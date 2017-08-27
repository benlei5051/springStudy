package com.andy;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.beans.PropertyVetoException;

/**
 * Created by jh on 2017/8/13.
 */
@Configuration
public class Conf {
    private final Logger logger= LoggerFactory.getLogger(Conf.class);
    @Autowired
    private Environment env;
    @Bean
    public ComboPooledDataSource dateSource() throws PropertyVetoException {
        ComboPooledDataSource com=new ComboPooledDataSource();
        com.setDriverClass("com.mysql.jdbc.Driver");
        logger.info("url:{}",env.getProperty("c3p0.url"));
        com.setUser(env.getProperty("c3p0.username"));
        com.setPassword(env.getProperty("c3p0.password"));
        com.setJdbcUrl(env.getProperty("c3p0.url"));
        return com;
    }
}
