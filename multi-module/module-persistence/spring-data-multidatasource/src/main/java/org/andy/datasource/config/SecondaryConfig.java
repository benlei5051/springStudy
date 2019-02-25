package org.andy.datasource.config;

import com.alibaba.druid.pool.xa.DruidXADataSource;
import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.boot.orm.jpa.hibernate.SpringJtaPlatform;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * ==========================================================================
 * SecondaryConfig
 * @Description: 次数据源
 * @author: haolin@pateo.com.cn
 * @date: 2019/2/13 16:09
 * @version V1.0
 * @Copyright 博泰悦臻网络技术服务有限公司
 * ==========================================================================
 */


@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef="entityManagerFactorySecondary",
        transactionManagerRef = "transactionManager", //配置 事物管理器  transactionManager
        basePackages= { "org.andy.datasource.repository.s" }) //设置Repository所在位置
public class SecondaryConfig {

    private final static String SLAVE_DATASOURCE_KEY = "slaveDataSource";

    @Autowired
    private JpaProperties jpaProperties;

    @Autowired
    private Environment environment;


    @Bean(name = "secondaryDataSource")
    public DataSource secondaryDataSource() {
        DruidXADataSource mysqlXaDataSource = new DruidXADataSource();
//        MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
        mysqlXaDataSource.setUrl(environment.getProperty("spring.datasource.slave.url"));
//        mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);
        mysqlXaDataSource.setPassword(environment.getProperty("spring.datasource.slave.password"));
        mysqlXaDataSource.setUsername(environment.getProperty("spring.datasource.slave.username"));
//        mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);

        com.atomikos.jdbc.AtomikosDataSourceBean xaDataSource = new com.atomikos.jdbc.AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(mysqlXaDataSource);
        xaDataSource.setUniqueResourceName("xads2");
        return xaDataSource;
    }


    private Map<String, Object> getVendorProperties(DataSource dataSource, SpringJtaPlatform springJtaPlatform) {
        Map<String, Object> vendorProperties = new LinkedHashMap<String, Object>();
        vendorProperties.putAll(jpaProperties.getHibernateProperties(dataSource));
        vendorProperties.put("hibernate.transaction.jta.platform", springJtaPlatform);
        vendorProperties.put("javax.persistence.transactionType", "JTA");
        return vendorProperties;
    }

    @Bean(name = "entityManagerFactorySecondary")
    public LocalContainerEntityManagerFactoryBean entityManagerFactorySecondary (EntityManagerFactoryBuilder builder, SpringJtaPlatform springJtaPlatform) {
        return builder.dataSource(secondaryDataSource()).packages("org.andy.datasource.domain.s")
                .properties(getVendorProperties(secondaryDataSource(),springJtaPlatform)).jta(true).build();
    }
//    private Map<String, String> getVendorProperties(DataSource dataSource) {
//        return jpaProperties.getHibernateProperties(dataSource);
//    }
//    @Bean(name = "entityManagerFactory")
//    public EntityManagerFactory entityManagerFactory(EntityManagerFactoryBuilder builder) {
//        return this.entityManagerFactorySecondary(builder).getObject();
//    }
}
