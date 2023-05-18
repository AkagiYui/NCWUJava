package com.akagiyui.ex4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author AkagiYui
 */

@Repository
public class AccountDaoImpl implements AccountDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // 添加账户
    @Override
    public int addAccount(Account account) {
        // 定义SQL
        String sql = "insert into account(username,balance) value(?,?)";
        // 定义数组来存放SQL语句中的参数
        Object[] obj = new Object[]{
                account.getUsername(),
                account.getBalance()
        };
        // 执行添加操作，返回的是受SQL语句影响的记录条数
        return jdbcTemplate.update(sql, obj);
    }

    // 更新账户
    @Override
    public int updateAccount(Account account) {
        String sql = "update account set username=?,balance=? where id = ?";
        Object[] params = new Object[]{
                account.getUsername(),
                account.getBalance(),
                account.getId()
        };
        return jdbcTemplate.update(sql, params);
    }

    // 删除账户
    @Override
    public int deleteAccount(int id) {
        String sql = "delete from account where id = ? ";
        return jdbcTemplate.update(sql, id);
    }

    // 通过id查询单个账户信息
    @Override
    public Account findAccountById(int id) {
        String sql = "select * from account where id = ?";
        // 创建一个新的BeanPropertyRowMapper对象
        // 它可以将数据库字段和一个JavaBean中的属性一一对应
        // 并自动为JavaBean的属性赋值
        // 所有的属性赋值完成后，将这个JavaBean对象返回
        try {
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Account.class), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    // 查询所有账户信息
    @Override
    public List<Account> findAllAccount() {
        // 定义SQL
        String sql = "select * from account";
        // 执行查询，将查询结果自动封装成List集合并且返回
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Account.class));
    }

    /**
     * 转账
     * srcAccountId：源账户id
     * destAccountId：目的账户id
     * money：转账金额
     */
    @Override
    @Transactional(
            propagation = Propagation.REQUIRED,
            isolation = Isolation.DEFAULT)
    public void transfer(int srcAccountId, int destAccountId, Double money) {
        // 先查询出两个人的账户信息
        Account srcAccount = findAccountById(srcAccountId);
        Account destAccount = findAccountById(destAccountId);
        // 然后修改金额
        srcAccount.setBalance(srcAccount.getBalance() - money);
        destAccount.setBalance(destAccount.getBalance() + money);
        // 执行更新
        updateAccount(srcAccount);
        // int i = 1 / 0;
        updateAccount(destAccount);
    }
}
