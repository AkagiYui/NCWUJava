package main.java.com.akagiyui.foundation.experiment.first;

import java.util.Scanner;

/*
1、	程序读入一个正整数，然后输入这个整数的位数
 */
public class CountLength {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // 创建Scanner对象
        System.out.print("请输入一个正整数：");
        int num = scanner.nextInt(); // 读入一个整数
        int length = 0; // 用于记录位数
        while (num > 0) { // 循环读取每一位
            num /= 10; // 去掉最后一位
            length++; // 位数加1
        }
        System.out.println("这个整数的位数是：" + length); // 输出位数
    }
}
