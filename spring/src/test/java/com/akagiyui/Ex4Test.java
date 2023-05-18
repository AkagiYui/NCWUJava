package com.akagiyui;

import com.akagiyui.ex4.Account;
import com.akagiyui.ex4.AccountDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author AkagiYui
 */
@ExtendWith(SpringExtension.class)
@EnableTransactionManagement
@ContextConfiguration(classes = {Ex4Application.class})
public class Ex4Test {
    @Resource
    JdbcTemplate jdbcTemplate;

    @Test
    void addAccountTest() {
        ApplicationContext applicationContext =  new AnnotationConfigApplicationContext(Ex4Application.class);
        AccountDao dao = applicationContext.getBean(AccountDao.class);

        Account accountNew = new Account();
        accountNew.setUsername("lisi");
        accountNew.setBalance(10000.0);

        dao.addAccount(accountNew);
    }

    @Test
    void queryForMapTest() {
        String sql = "select * from account where id = ?";
        System.out.println(jdbcTemplate.queryForMap(sql, 4));
    }

    @Test
    void queryForObjectTest() {
        String sql = "select count(*) from account";
        System.out.println(jdbcTemplate.queryForObject(sql, Integer.class));
    }

    @Test
    void updateAccount() {
        ApplicationContext applicationContext =  new AnnotationConfigApplicationContext(Ex4Application.class);
        AccountDao dao = applicationContext.getBean(AccountDao.class);

        Account accountNew = new Account();
        accountNew.setId(1);
        accountNew.setUsername("lisi");
        accountNew.setBalance(1050314133.0);

        dao.updateAccount(accountNew);
    }

    @Test
    void deleteAccount() {
        ApplicationContext applicationContext =  new AnnotationConfigApplicationContext(Ex4Application.class);
        AccountDao dao = applicationContext.getBean(AccountDao.class);

        dao.deleteAccount(1);
    }

    @Test
    @Transactional
    @Rollback
    public void findAccountById() {
        ApplicationContext applicationContext =  new AnnotationConfigApplicationContext(Ex4Application.class);
        AccountDao dao = applicationContext.getBean(AccountDao.class);

        Account accountNew = new Account();
        accountNew.setUsername("ddi");
        accountNew.setBalance(233.0);
        dao.addAccount(accountNew);

        System.out.println(dao.findAccountById(4));
    }

    @Test
    void findAllAccount() {
        ApplicationContext applicationContext =  new AnnotationConfigApplicationContext(Ex4Application.class);
        AccountDao dao = applicationContext.getBean(AccountDao.class);

        System.out.println(dao.findAllAccount());
    }

    @Test
    void transfer() {
        ApplicationContext applicationContext =  new AnnotationConfigApplicationContext(Ex4Application.class);
        AccountDao dao = applicationContext.getBean(AccountDao.class);

        dao.transfer(4, 5, 100.0);
    }
}
