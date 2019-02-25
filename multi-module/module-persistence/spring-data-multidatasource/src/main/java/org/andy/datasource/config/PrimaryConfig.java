package org.andy.datasource.config;

import com.alibaba.druid.pool.xa.DruidXADataSource;
import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;
import lombok.extern.slf4j.Slf4j;
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
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

/**
 * ==========================================================================
 * PrimaryConfig
 * @Description: 主数据源
 * @author: haolin@pateo.com.cn
 * @date: 2019/2/13 16:04
 * @version V1.0
 * @Copyright 博泰悦臻网络技术服务有限公司
 * ==========================================================================
 */
@Slf4j
@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef="entityManagerFactoryPrimary",
        transactionManagerRef = "transactionManager", //配置 事物管理器  transactionManager
        basePackages= { "org.andy.datasource.repository.p" }) //设置Repository所在位置
public class PrimaryConfig {

    private final static String MASTER_DATASOURCE_KEY = "masterDataSource";
//    private final static String SLAVE_DATASOURCE_KEY = "slaveDataSource";

    @Autowired
    private JpaProperties jpaProperties;

    @Autowired
    private Environment environment;


    @Primary
    @Bean(name = "primaryDataSource")
    public DataSource primaryDataSource() {
        log.info("create master datasource...");
        DruidXADataSource mysqlXaDataSource = new DruidXADataSource();
//        MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
        mysqlXaDataSource.setUrl(environment.getProperty("spring.datasource.master.url"));
//        mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);
        mysqlXaDataSource.setPassword(environment.getProperty("spring.datasource.master.password"));
        mysqlXaDataSource.setUsername(environment.getProperty("spring.datasource.master.username"));
//        mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);

        com.atomikos.jdbc.AtomikosDataSourceBean xaDataSource = new com.atomikos.jdbc.AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(mysqlXaDataSource);
        xaDataSource.setUniqueResourceName("xads1");
        return xaDataSource;
    }


    @Primary
    @Bean(name = "entityManagerFactoryPrimary")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryPrimary (EntityManagerFactoryBuilder builder, SpringJtaPlatform springJtaPlatform) {
        return builder.dataSource(primaryDataSource()).packages("org.andy.datasource.domain.p")
                .properties(getVendorProperties(primaryDataSource(),springJtaPlatform)).jta(true).build();
    }

    private Map<String, Object> getVendorProperties(DataSource dataSource, SpringJtaPlatform springJtaPlatform) {
        Map<String, Object> vendorProperties = new LinkedHashMap<String, Object>();
        vendorProperties.putAll(jpaProperties.getHibernateProperties(dataSource));
        vendorProperties.put("hibernate.transaction.jta.platform", springJtaPlatform);
        vendorProperties.put("javax.persistence.transactionType", "JTA");
        return vendorProperties;
    }
//    @Primary
//    @Bean(name = "entityManagerFactory")
//    public EntityManagerFactory entityManagerFactory(EntityManagerFactoryBuilder builder) {
//        return this.entityManagerFactoryPrimary(builder).getObject();
//    }

}
