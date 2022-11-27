package main.java.com.akagiyui.foundation.experiment.fourth;

import java.util.Scanner;

/*
测试类，创建三个手机对象，键盘录入信息并存入数组当中。
 */
public class PhoneTest {
    public static void main(String[] args) {
        var phones = new Phone[3];
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < phones.length; i++) {
            phones[i] = new Phone();
            System.out.print("请输入第" + (i + 1) + "个手机的id：");
            phones[i].setId(Integer.parseInt(scanner.nextLine()));
            System.out.println("请输入第" + (i + 1) + "个手机的品牌：");
            phones[i].setBrand(scanner.nextLine());
            System.out.println("请输入第" + (i + 1) + "个手机的价格：");
            phones[i].setPrice(Double.parseDouble(scanner.nextLine()));
            System.out.println("请输入第" + (i + 1) + "个手机的型号：");
            phones[i].setModel(scanner.nextLine());
            System.out.println("请输入第" + (i + 1) + "个手机的库存：");
            phones[i].setStock(Integer.parseInt(scanner.nextLine()));
        }
    }
}
