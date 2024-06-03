package com.demo.multipledb.mysql.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "mySqlEntityManagerFactoryBean",
        basePackages = {"com.demo.multipledb.mysql.repo"},
        transactionManagerRef = "mySqlTransactionManagerBean"
)
public class MySqlConfig {

    @Autowired
    private Environment environment;

    @Bean(name = "secondDataSource")
    public DataSource mySqlDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(environment.getProperty("second.datasource.url"));
        dataSource.setUsername(environment.getProperty("second.datasource.username"));
        dataSource.setPassword(environment.getProperty("second.datasource.password"));
        dataSource.setDriverClassName(environment.getProperty("second.datasource.driver-class-name"));

        return dataSource;
    }

    // entityManagerFactory
    @Bean(name = "mySqlEntityManagerFactoryBean")
    public LocalContainerEntityManagerFactoryBean mySqlEntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(mySqlDataSource());
        factoryBean.setPackagesToScan("com.demo.multipledb.mysql.entities");

        Map<String, String> jpaProperties = new HashMap<>();
        jpaProperties.put("hibernate.dialect", environment.getProperty("second.hibernate.dialect"));
        jpaProperties.put("hibernate.show_sql", environment.getProperty("second.hibernate.show_sql"));
        jpaProperties.put("hibernate.hbm2ddl.auto", environment.getProperty("second.hibernate.hbm2ddl.auto"));

        factoryBean.setJpaPropertyMap(jpaProperties);

        JpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        factoryBean.setJpaVendorAdapter(adapter);

        return factoryBean;
    }

    // transactionManager
    @Bean(name = "mySqlTransactionManagerBean")
    public PlatformTransactionManager mySqlTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(mySqlEntityManagerFactory().getObject());
        return transactionManager;
    }

}
