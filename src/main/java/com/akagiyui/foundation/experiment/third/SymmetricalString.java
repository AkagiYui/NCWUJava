package main.java.com.akagiyui.foundation.experiment.third;
/*
接受用户输入的一个字符串，判断他们是否是对称字符串。

提示：两种方法均可

（1）第一种方法，利用字符串倒着遍历，for (int i = str.length()-1; i >= 0; i--)
（2）第二种方法StringBuilder reverse  (r)  反转容器的对象
 */
public class SymmetricalString {
    public static void main(String[] args) {
        var scanner = new java.util.Scanner(System.in);
        System.out.print("请输入一个字符串：");
        var str = scanner.nextLine();

        // 第一种方法
        var reverseStr = "";
        for (int i = str.length() - 1; i >= 0; i--) {
            reverseStr += str.charAt(i);  // charAt() 方法用于返回指定索引处的字符。索引范围为从 0 到 length() - 1。
        }
        if (str.equals(reverseStr)) {
            System.out.println("是对称字符串！");
        } else {
            System.out.println("不是对称字符串！");
        }

        var reverseStr2 = new StringBuilder(str).reverse().toString();  // StringBuilder reverse 反转容器的对象
        if (str.equals(reverseStr2)) {
            System.out.println("是对称字符串！");
        } else {
            System.out.println("不是对称字符串！");
        }
    }
}
