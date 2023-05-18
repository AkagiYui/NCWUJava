package com.akagiyui.ex4;

import java.util.List;

/**
 * @author AkagiYui
 */

public interface AccountDao {
    // 添加
    int addAccount(Account account);
    // 更新
    int updateAccount(Account account);
    // 删除
    int deleteAccount(int id);
    // 通过id查询
    Account findAccountById(int id);
    // 查询所有
    List<Account> findAllAccount();
    // 转账：一个账户减去，一个账户加
    void transfer(int srcAccountId, int destAccountId, Double money);
}
