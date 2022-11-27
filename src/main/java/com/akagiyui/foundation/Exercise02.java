package main.java.com.akagiyui.foundation;

import java.util.Random;
import java.util.Scanner;

public class Exercise02 {
    // 随机生成一个1-100的整数，猜数字

    public static void main(String[] args) {
        int target = new Random().nextInt(100) + 1;
        int guess;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.print("请输入一个1-100的整数：");
            guess = scanner.nextInt();
            if (guess < target) {
                System.out.println("小了");
            } else {
                System.out.println("大了");
            }
        } while (guess != target);
        System.out.println("好了");
    }
}
