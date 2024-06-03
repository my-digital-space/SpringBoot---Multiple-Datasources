package com.demo.multipledb.postgresql.config;

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
        entityManagerFactoryRef = "postgreSqlEntityManagerFactoryBean",
        basePackages = {"com.demo.multipledb.postgresql.repo"},
        transactionManagerRef = "postgreSqlTransactionManagerBean"
)
public class PostgreSqlConfig {

    @Autowired
    private Environment environment;

    @Bean(name = "firstDataSource")
    public DataSource postgreSqlDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(environment.getProperty("spring.datasource.url"));
        dataSource.setUsername(environment.getProperty("spring.datasource.username"));
        dataSource.setPassword(environment.getProperty("spring.datasource.password"));
        dataSource.setDriverClassName(environment.getProperty("spring.datasource.driver-class-name"));

        return dataSource;
    }

    // entityManagerFactory
    @Bean(name = "postgreSqlEntityManagerFactoryBean")
    public LocalContainerEntityManagerFactoryBean postgreSqlEntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(postgreSqlDataSource());
        factoryBean.setPackagesToScan("com.demo.multipledb.postgresql.entities");

        Map<String, String> jpaProperties = new HashMap<>();
        jpaProperties.put("hibernate.dialect", environment.getProperty("spring.hibernate.dialect"));
        jpaProperties.put("hibernate.show_sql", environment.getProperty("spring.hibernate.show_sql"));
        jpaProperties.put("hibernate.hbm2ddl.auto", environment.getProperty("spring.hibernate.hbm2ddl.auto"));

        factoryBean.setJpaPropertyMap(jpaProperties);

        JpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        factoryBean.setJpaVendorAdapter(adapter);

        return factoryBean;
    }

    // transactionManager
    @Bean(name = "postgreSqlTransactionManagerBean")
    public PlatformTransactionManager postgreSqlTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(postgreSqlEntityManagerFactory().getObject());
        return transactionManager;
    }

}
