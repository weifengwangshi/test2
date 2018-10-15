package com.basefrm.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

/**

 * Created by onion on 2017-03-30 15:18.

 */

@Configuration
public class HibernateConfiguration {

    //配置sessionFactory

    @Bean

    public
    LocalSessionFactoryBean sessionFactory(@Qualifier("dataSource") DataSource dataSource,
                                           @Value("${jpa.properties.hibernate.packageScan}")String packageScan,
                                           @Value("${jpa.properties.hibernate.dialect}")String dialect,
                                           @Value("${jpa.show-sql}") String showSql,
                                           @Value("${jpa.properties.hibernate.format_sql}") String formatSql
//                                           @Value("${spring.jpa.properties.hibernate.use_sql_comments}") String useSqlComments,
//                                           @Value("${spring.jpa.hibernate.ddl-auto}") String ddlAuto
    ) {
        LocalSessionFactoryBean localSessionFactoryBean = new   LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(dataSource);
        localSessionFactoryBean.setPackagesToScan(packageScan);
        Properties properties = new  Properties();
        properties.setProperty("hibernate.dialect", dialect);
        properties.setProperty("hibernate.show_sql", showSql);
        properties.setProperty("hibernate.format_sql", formatSql);
//        properties.setProperty("hibernate.use_sql_comments", useSqlComments);
//        properties.setProperty("hibernate.hbm2ddl.auto", ddlAuto);
        localSessionFactoryBean.setHibernateProperties(properties);
        return localSessionFactoryBean;
    }

    //配置hibernate事务处理

    @Bean

    public
    HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new  HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);
        return transactionManager;

    }

}
