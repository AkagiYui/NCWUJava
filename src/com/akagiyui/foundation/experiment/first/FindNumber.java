package com.akagiyui.foundation.experiment.first;

import java.util.Scanner;

/*
搜索数字
要求用户在一系列给定的数据中，搜索某一个数是否存在，并确定其位置
 */
public class FindNumber {
    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}; // 定义一个数组
        Scanner scanner = new Scanner(System.in); // 创建Scanner对象
        System.out.print("请输入一个数字：");
        int num = scanner.nextInt(); // 读入一个整数
        int index = -1; // 用于记录数字的位置
        for (int i = 0; i < numbers.length; i++) { // 循环遍历数组
            if (numbers[i] == num) { // 判断是否找到
                index = i; // 记录位置
                break; // 跳出循环
            }
        }
        if (index == -1) { // 判断是否找到
            System.out.println("没有找到这个数字");
        } else {
            System.out.println("这个数字的位置是：" + index);
        }
    }
}
