package main.java.com.akagiyui.foundation;

import java.util.Scanner;

public class Exercise07 {
    //已知正确的用户名和密码，模拟用户登录，给三次机会，登陆后给出相应的提示
    static String USERNAME = "admin";
    static String PASSWORD = "123456";
    static int MAX_TIMES = 3;

    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        var is_login = false;
        for (var current_times = 0; current_times < MAX_TIMES && !is_login; current_times++) {
            System.out.print("请输入用户名：");
            var username = scanner.nextLine();
            while (username.isEmpty()) {
                System.out.print("用户名不能为空，请重新输入：");
                username = scanner.nextLine();
            }
            System.out.print("请输入密码：");
            var password = scanner.nextLine();
            while (password.isEmpty()) {
                System.out.print("密码不能为空，请重新输入：");
                password = scanner.nextLine();
            }

            if (password.equals(PASSWORD) && username.equals(USERNAME)) {
                is_login = true;
            } else {
                System.out.printf("用户名或密码错误！您还有 %d 次机会！%n", MAX_TIMES - current_times - 1);
            }
        }

        if (is_login) {
            System.out.println("登录成功！");
        } else {
            System.out.println("登录失败，请在24小时后重试！");
        }
    }
}
