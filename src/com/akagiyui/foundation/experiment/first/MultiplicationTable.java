package com.akagiyui.foundation.experiment.first;

public class MultiplicationTable {  // 类名，使用大驼峰命名法
    public static void main(String[] args) {  // main方法，程序入口
        for (int i = 1; i <= 9; i++) {  // 外层循环，控制行数
            for (int j = 1; j <= i; j++) {  // 内层循环，控制列数
                System.out.print(j + "*" + i + "=" + i * j + "\t");  // 打印乘法表
            }
            System.out.println();  // 换行
        }
    }
}
