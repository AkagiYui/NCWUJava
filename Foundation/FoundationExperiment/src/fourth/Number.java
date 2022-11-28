package fourth;
/*
4、模拟简单计算器，定义Number类。要求如下：
（1）属性：整型数据n1和n2，构造方法和set、get方法
（2）方法：为该类定义 加法addition（），减法subtration（），乘法multiplication（），除法division（）等实例方法。
（3）测试类，创建对象，检测计算机的各项功能。
 */
public class Number {
    private int n1;
    private int n2;

    public Number(int n1, int n2) {
        this.n1 = n1;
        this.n2 = n2;
    }

    public int getN1() {
        return n1;
    }

    public void setN1(int n1) {
        this.n1 = n1;
    }

    public int getN2() {
        return n2;
    }

    public void setN2(int n2) {
        this.n2 = n2;
    }

    public int addition() {
        return n1 + n2;
    }

    public int subtraction() {
        return n1 - n2;
    }

    public int multiplication() {
        return n1 * n2;
    }

    public double division() {
        return (double) n1 / n2;
    }
}
