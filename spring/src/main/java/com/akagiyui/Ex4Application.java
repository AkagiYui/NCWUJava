package com.akagiyui;

import com.akagiyui.ex4.Account;
import com.akagiyui.ex4.AccountDao;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * @author AkagiYui
 */
@Configuration
@ComponentScan("com.akagiyui.ex4")
public class Ex4Application {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost/spring?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai&useSSL=false");
        ds.setUsername("root");
        ds.setPassword("");
        return ds;
    }

    @Bean
    @Qualifier("transactionManager")
    public PlatformTransactionManager txManager(DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    public static void main(String[] args) {
        ApplicationContext applicationContext =  new AnnotationConfigApplicationContext(Ex4Application.class);
        AccountDao dao = applicationContext.getBean(AccountDao.class);

        Account accountNew = new Account();
        accountNew.setUsername("lisi");
        accountNew.setBalance(10000.0);

        dao.addAccount(accountNew);
    }
}
