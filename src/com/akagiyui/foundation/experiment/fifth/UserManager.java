package com.akagiyui.foundation.experiment.fifth;

import com.akagiyui.foundation.experiment.fifth.entity.User;

import java.util.HashMap;
import java.util.Scanner;

public class UserManager {
    private final Scanner scanner = new Scanner(System.in);
    private final HashMap<String, User> users; // 用户列表

    public UserManager() {
        HashMap<String, User> users1 = Utils.loadObject("users.dat");
        if (users1 == null) {
            users1 = new HashMap<>();
        }
        users = users1;
    }

    public void saveData() {
        Utils.saveObject(users, "users.dat");
    }

    @SuppressWarnings("UnusedReturnValue")
    public User register() {
        System.out.println("===用户注册===");
        var user = new User();

        while (user.getUsername() == null) {
            System.out.print("请输入用户名：");
            if (users.containsKey(user.getUsername())) {
                System.out.println("用户已存在！");
            } else {
                user.setUsername(scanner.nextLine());
            }
        }

        while (user.getPassword() == null) {
            System.out.print("请输入密码：");
            var password = scanner.nextLine();
            System.out.print("请再次输入密码：");
            var password2 = scanner.nextLine();
            if (password.equals(password2)) {
                user.setPassword(password);
            } else {
                System.out.print("两次输入的密码不一致！");
            }
        }

        while (user.getId() == null) {
            System.out.print("请输入身份证号：");
            user.setId(scanner.nextLine());
        }

        while (user.getPhone() == null ||user.getPhone().isEmpty()) {
            System.out.print("请输入手机号：");
            user.setPhone(scanner.nextLine());
        }

        if (user.isLegal()) {
            System.out.println("注册成功。");
            users.put(user.getUsername(), user);
            saveData();
            return user;
        } else {
            System.out.println("注册失败！");
            return null;
        }
    }

    public User login() {
        System.out.println("===用户登录===");
        for (int i = 0; i < 3; i++) {
            System.out.print("请输入用户名：");
            var username = scanner.nextLine();
            if (!users.containsKey(username)) {
                System.out.println("用户未注册！");
                return null;
            }

            System.out.print("请输入密码：");
            var password = scanner.nextLine();

            String verifyCode;
            String inputVerifyCode;
            do {
                verifyCode = Utils.generateVerifyCode();
                System.out.println("验证码：" + verifyCode);
                System.out.print("请输入验证码：");
                inputVerifyCode = scanner.nextLine();
            } while (!inputVerifyCode.equals(verifyCode));

            var user = users.get(username);
            if (user.getPassword().equals(password)) {
                System.out.println("登录成功。");
                return user;
            } else {
                System.out.println("密码错误！");
            }
        }
        System.out.println("登录失败！");
        return null;
    }

    @SuppressWarnings("UnusedReturnValue")
    public boolean forgetPassword() {
        System.out.println("===忘记密码===");
        System.out.print("请输入用户名：");
        var username = scanner.nextLine();
        if (!users.containsKey(username)) {
            System.out.println("用户未注册！");
            return false;
        }

        System.out.print("请输入身份证号：");
        var id = scanner.nextLine();

        System.out.print("请输入手机号：");
        var phone = scanner.nextLine();

        var user = users.get(username);
        if (user.getId().equals(id) && user.getPhone().equals(phone)) {
            System.out.print("请输入新密码：");
            var password = scanner.nextLine();
            System.out.print("请再次输入新密码：");
            var password2 = scanner.nextLine();
            if (password.equals(password2)) {
                user.setPassword(password);
                System.out.println("修改成功，请重新登录。");
                saveData();
            } else {
                System.out.println("两次输入的密码不一致。");
                return false;
            }
        } else {
            System.out.println("账号信息不匹配，修改失败。");
            return false;
        }
        return true;
    }
}
