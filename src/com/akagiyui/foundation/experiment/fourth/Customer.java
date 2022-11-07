package com.akagiyui.foundation.experiment.fourth;
/*
2、编写Account类，代表银行账户。要求如下：
（1）属性：账户id，余额balance，年利率annualInterestRate
（2）提供set、get方法，取款方法withdraw（），存款方法deposit（）
（3）编写测试类，创建一个Customer，名字叫“李想”，账号为“20210035”，余额为“5000元”，年利率为1.35
（4）对李想操作，存入500元，再取出2000元，再取出4000元。
（5） 打印信息： 成功取出2000元，余额不足，取钱失败
 */
public class Customer {
    private String name;
    private Account account;

    public Customer(String name, Account account) {
        this.name = name;
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
