package experiment.first;

import java.util.Scanner;

/*
2、	用户输入一系列的正整数，最后输入-1表示结束，然后计算出这些数字的平均数，输出输入数字的个数和平均数。
 */
public class CalcAverage {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int sum = 0; // 用于记录总和
        int count = 0; // 用于记录位数
        while (true) {
            System.out.print("请输入一个正整数：");
            int num = scanner.nextInt(); // 读入一个整数
            if (num == -1) {
                break; // 输入-1时结束循环
            }
            sum += num; // 累加

            int length = 0; // 用于记录位数
            while (num > 0) { // 循环读取每一位
                num /= 10; // 去掉最后一位
                length++; // 位数加1
            }
            count += length; // 累加位数
        }
        System.out.println("输入数字的个数是：" + count);
        System.out.println("输入数字的平均数是：" + sum / count);
    }
}
