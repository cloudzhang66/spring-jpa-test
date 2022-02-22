package com.iless.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = {"com.iless"})
@EnableJpaRepositories(basePackages = {"com.iless.entity"})
public class JpaConfig {

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
        LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
        bean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        bean.setPackagesToScan("com.iless");

        Properties pp = new Properties();
        pp.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        pp.setProperty("hibernate.connection.url", "jdbc:mysql://192.168.1.80:3306/jpatest");
        pp.setProperty("hibernate.connection.username", "test");
        pp.setProperty("hibernate.connection.password", "12345678");
        pp.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        pp.setProperty("hibernate.connection.provider_class", "org.hibernate.connection.C3P0ConnectionProvider");
        pp.setProperty("hibernate.c3p0.min_size", "1");
        pp.setProperty("hibernate.c3p0.max_size", "10");
        pp.setProperty("hibernate.hbm2ddl.auto", "update");
        pp.setProperty("hibernate.show_sql", "true");
        pp.setProperty("format_sql", "true");
        bean.setJpaProperties(pp);
        return bean;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf){
        JpaTransactionManager jtm = new JpaTransactionManager();
        jtm.setEntityManagerFactory(emf);
        return jtm;

    }
}
