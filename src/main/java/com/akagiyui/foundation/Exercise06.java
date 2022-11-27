package main.java.com.akagiyui.foundation;

import java.util.Scanner;

public class Exercise06 {
    // 编写程序，将某一个输出位数不正确的正整数按照标准的三维分节格式输出，例如，用户输入62395047018时，程序应该输出62,395,047,018

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入一个正整数：");
        String num = scanner.next();
        int len = num.length();  // 获取字符串长度

        StringBuilder result = new StringBuilder();
        if (len <= 3) {
            result.append(num);
        } else {
            int count = len / 3;  // 计算需要分成几组
            int remainder = len % 3;  // 计算剩下的位数
            if (remainder != 0) {  // 先处理最前面的一组
                result.append(num, 0, remainder);
                result.append(",");
            }
            for (int i = 0; i < count; i++) {
                result.append(num, remainder + i * 3, remainder + i * 3 + 3);
                if (i != count - 1) {
                    result.append(",");
                }
            }
        }
        System.out.println(result);
    }
}
