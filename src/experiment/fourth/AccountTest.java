package experiment.fourth;
/*
2、编写Account类，代表银行账户。要求如下：
（1）属性：账户id，余额balance，年利率annualInterestRate
（2）提供set、get方法，取款方法withdraw（），存款方法deposit（）
（3）编写测试类，创建一个Customer，名字叫“李想”，账号为“20210035”，余额为“5000元”，年利率为1.35
（4）对李想操作，存入500元，再取出2000元，再取出4000元。
（5） 打印信息： 成功取出2000元，余额不足，取钱失败
 */
public class AccountTest {
    public static void main(String[] args) {
        var c = new Customer("李想", new Account("20210035", 5000, 1.35));
        c.getAccount().deposit(500); // 存入500元
        c.getAccount().withdraw(2000); // 取出2000元
        c.getAccount().withdraw(4000); // 取出4000元
    }
}
