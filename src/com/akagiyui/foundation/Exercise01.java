package com.akagiyui.foundation;

import java.util.Scanner;

public class Exercise01 {
    // 键盘输入一个大于2的正整数，输出其平方根

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入一个大于2的正整数：");
        int num = scanner.nextInt();
        if (num <= 2) {
            System.out.println("输入错误！");
        } else {
            System.out.println("平方根为：" + Math.sqrt(num));
        }
    }
}
