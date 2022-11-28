package first;

import java.util.Scanner;

/*
5、	投票题（数组解决）
有10位候选人参与投票，请统计每个人的票数。
首先给每个人一个编号，[0,9]范围内的整数，统计每一种数字出现的次数，输入-1表示结束。
即 0 号选手输入多少次，就是得票数
 1 输入多少次………
 */
public class CountVote {
    public static void main(String[] args) {
        int[] arr = new int[10]; // 创建一个长度为10的数组
        Scanner scanner = new Scanner(System.in); // 创建Scanner对象
        System.out.print("请输入候选人编号：");
        int num = scanner.nextInt(); // 读入一个整数
        while (num != -1) { // 循环读取每一个候选人编号
            arr[num]++; // 投票
            System.out.print("请输入候选人编号：");
            num = scanner.nextInt(); // 读入一个整数
        }
        for (int i = 0; i < 10; i++) { // 循环输出每一个候选人的票数
            System.out.println(i + " 输入" + arr[i] + "次");
        }
    }
}
