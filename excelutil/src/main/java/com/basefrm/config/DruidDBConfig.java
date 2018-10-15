package com.basefrm.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.support.spring.stat.DruidStatInterceptor;
import com.basefrm.util.AESUtils;
import com.basefrm.util.Base64Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.Advisor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Created by arnold.zhu on 6/13/2017.
 */
@Configuration
public class DruidDBConfig  {

    private Logger logger = LoggerFactory.getLogger(DruidDBConfig.class);

    @Value("${spring.datasource.url}")
    private String urls;
    @Value("${spring.datasource.username}")
    private String usernames;
    @Value("${spring.datasource.password}")
    private String passwords;
    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.initialSize}")
    private int initialSize;

    @Value("${spring.datasource.minIdle}")
    private int minIdle;

    @Value("${spring.datasource.maxActive}")
    private int maxActive;

    @Value("${spring.datasource.maxWait}")
    private int maxWait;

    @Value("${spring.datasource.timeBetweenEvictionRunsMillis}")
    private int timeBetweenEvictionRunsMillis;

    @Value("${spring.datasource.minEvictableIdleTimeMillis}")
    private int minEvictableIdleTimeMillis;

    @Value("${spring.datasource.validationQuery}")
    private String validationQuery;

    @Value("${spring.datasource.testWhileIdle}")
    private boolean testWhileIdle;

    @Value("${spring.datasource.testOnBorrow}")
    private boolean testOnBorrow;

    @Value("${spring.datasource.testOnReturn}")
    private boolean testOnReturn;

    @Value("${spring.datasource.filters}")
    private String filters;

    @Value("${spring.datasource.logSlowSql}")
    private String logSlowSql;

    public DruidDBConfig() throws Exception {
    }

    @Bean
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean reg = new ServletRegistrationBean();
        reg.setServlet(new StatViewServlet());
        reg.addUrlMappings("/druid/*");
        try {
            reg.addInitParameter("loginUsername", new String(AESUtils.decrypt(Base64Utils.decode(usernames), AESUtils.KEY)));
            reg.addInitParameter("loginPassword", new String(AESUtils.decrypt(Base64Utils.decode(passwords), AESUtils.KEY)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        reg.addInitParameter("logSlowSql", logSlowSql);
        return reg;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        filterRegistrationBean.addInitParameter("profileEnable", "true");
        return filterRegistrationBean;
    }
    //添加aop的advice

    @Bean

    public DruidStatInterceptor druidStatInterceptor() {

        return new DruidStatInterceptor();

    }
    //添加aop的pointcut

    @Bean

    public JdkRegexpMethodPointcut jdkRegexpMethodPointcut() {

        JdkRegexpMethodPointcut jdkRegexpMethodPointcut
                = new JdkRegexpMethodPointcut();

        jdkRegexpMethodPointcut.setPatterns("com.basefrm.dao.*");

        return jdkRegexpMethodPointcut;

    }

    //设置默认的aop配置对应的是原来的<aop:advisor>

    @Bean

    public Advisor druidAdvisor() {

        DefaultPointcutAdvisor defaultPointcutAdvisor = new DefaultPointcutAdvisor();

        defaultPointcutAdvisor.setPointcut(jdkRegexpMethodPointcut());

        defaultPointcutAdvisor.setAdvice(druidStatInterceptor());

        return defaultPointcutAdvisor;

    }


    @Bean("dataSource")
    public DataSource druidDataSource() {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(urls);
//        datasource.setUsername(username);
//        datasource.setPassword(password);
        try {
//            datasource.setUrl(new String(AESUtils.decrypt(Base64Utils.decode(urls), AESUtils.KEY)));
            datasource.setUsername(new String(AESUtils.decrypt(Base64Utils.decode(usernames), AESUtils.KEY)));
            datasource.setPassword(new String(AESUtils.decrypt(Base64Utils.decode(passwords), AESUtils.KEY)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        datasource.setDriverClassName(driverClassName);
        datasource.setInitialSize(initialSize);
        datasource.setMinIdle(minIdle);
        datasource.setMaxActive(maxActive);
        datasource.setMaxWait(maxWait);
        datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        datasource.setValidationQuery(validationQuery);
        datasource.setTestWhileIdle(testWhileIdle);
        datasource.setTestOnBorrow(testOnBorrow);
        datasource.setTestOnReturn(testOnReturn);
        try {
            datasource.setFilters(filters);
        } catch (SQLException e) {
            logger.error("druid configuration initialization filter", e);
        }
        return datasource;
    }

}