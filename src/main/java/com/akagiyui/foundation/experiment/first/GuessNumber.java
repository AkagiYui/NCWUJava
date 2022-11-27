package main.java.com.akagiyui.foundation.experiment.first;

import java.util.Scanner;

/*
3、	由计算机随机给出一个数，用户来猜这个数是多少，输入的数大于该数，则输出“大了”，输入的数小于该数则输出“小了”，并统计猜数的次数
提示：Math.random()函数的含义是指随机给出[0,1)之间的一个浮点数，
如果现在要得到1-100之间的一个数字：int number=(int)(Math.random()*100+1);
 */
public class GuessNumber {
    public static void main(String[] args) {
        int number = (int) (Math.random() * 100 + 1); // 随机生成一个[1,100]之间的整数
        Scanner scanner = new Scanner(System.in); // 创建Scanner对象
        int count = 0; // 记录猜数的次数
        while (true) { // 无限循环
            System.out.print("请输入一个[1,100]之间的整数：");
            int guess = scanner.nextInt(); // 读入一个整数
            count++; // 猜数次数加1
            if (guess == number) { // 猜对了
                System.out.println("恭喜你猜对了！");
                break; // 退出循环
            } else if (guess > number) { // 猜大了
                System.out.println("大了");
            } else { // 猜小了
                System.out.println("小了");
            }
        }
        System.out.println("你总共猜了" + count + "次！");
    }
}
