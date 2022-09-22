package experiment.third;
/*
4、模拟简单计算器，定义Number类。要求如下：
（1）属性：整型数据n1和n2，构造方法和set、get方法
（2）方法：为该类定义 加法addition（），减法subtration（），乘法multiplication（），除法division（）等实例方法。
（3）测试类，创建对象，检测计算机的各项功能。
 */

public class NumberTest {
    public static void main(String[] args) {
        Number number = new Number(10, 5);
        System.out.println("n1 + n2 = " + number.addition());
        System.out.println("n1 - n2 = " + number.subtraction());
        System.out.println("n1 * n2 = " + number.multiplication());
        System.out.println("n1 / n2 = " + number.division());
    }
}
