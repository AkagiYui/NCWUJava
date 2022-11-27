package main.java.com.akagiyui.foundation.experiment.first;

import java.util.Scanner;

/*
输入一个数字，将其逆序输出
 */
public class ReverseNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入一个整数：");
        int num = scanner.nextInt();
        int reverseNum = 0; //记录结果
        while (num > 0) {
            reverseNum = reverseNum * 10 + num % 10; // 取出最后一位
            num /= 10; // 去掉最后一位
        }
        System.out.println("逆序后的数字是：" + reverseNum);
    }
}
