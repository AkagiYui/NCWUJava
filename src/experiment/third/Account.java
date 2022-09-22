package experiment.third;
/*
2、编写Account类，代表银行账户。要求如下：
（1）属性：账户id，余额balance，年利率annualInterestRate
（2）提供set、get方法，取款方法withdraw（），存款方法deposit（）
（3）编写测试类，创建一个Customer，名字叫“李想”，账号为“20210035”，余额为“5000元”，年利率为1.35
（4）对李想操作，存入500元，再取出2000元，再取出4000元。
（5） 打印信息： 成功取出2000元，余额不足，取钱失败
 */
public class Account {
    private String id;
    private double balance;
    private double annualInterestRate;

    public Account(String id, double balance, double annualInterestRate) {
        this.id = id;
        this.balance = balance;
        this.annualInterestRate = annualInterestRate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getAnnualInterestRate() {
        return annualInterestRate;
    }

    public void setAnnualInterestRate(double annualInterestRate) {
        this.annualInterestRate = annualInterestRate;
    }

    public void withdraw(double money) {
        if (balance >= money) {
            balance -= money;
            System.out.println("成功取出" + money + "元");
        } else {
            System.out.println("余额不足，取钱失败");
        }
    }

    public void deposit(double money) {
        balance += money;
    }
}
