package com.akagiyui.foundation.experiment.third;
/*
接受用户输入的用户名和密码，判断用户名和密码是否正确，允许输入次数三次。
 */
public class ValidPassword {
    public static void main(String[] args) {
        var username = "admin";
        var password = "123456";
        var scanner = new java.util.Scanner(System.in);
        for (int i = 0; i < 3; i++) {
            System.out.print("请输入用户名：");
            var inputUsername = scanner.nextLine();
            System.out.print("请输入密码：");
            var inputPassword = scanner.nextLine();
            if (inputUsername.equals(username) && inputPassword.equals(password)) { // 字符串比较使用equals方法
                System.out.println("登录成功！");
                break;
            } else {
                System.out.println("用户名或密码错误！");
            }
        }
    }
}
