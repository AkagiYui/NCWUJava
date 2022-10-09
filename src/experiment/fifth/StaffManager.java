package experiment.fifth;

import experiment.fifth.Entity.Staff;

import java.util.HashMap;
import java.util.Scanner;

public class StaffManager {
    private final HashMap<String, Staff> staffs; // 员工列表
    private final UserManager userManager = new UserManager(); // 用户管理
    private boolean logged = false; // 已登录

    public boolean isLogged() {
        return logged;
    }

    public StaffManager() {
        HashMap<String, Staff> staffs1;
        staffs1 = Utils.loadObject("staffs.dat");
        if (staffs1 == null) {
            staffs1 = new HashMap<>();
        }
        staffs = staffs1;
    }

    public void quit() {
        userManager.saveData();
        Utils.saveObject(staffs, "staffs.dat");
    }

    public void init() {
        // 在此处进行 登录/注册/忘记密码 操作
        var menu = """
                --------欢迎来到员工管理系统----------
                1.登录
                2.注册
                3.忘记密码
                4.退出
                请输入您的选择：""";
        var scanner = new Scanner(System.in);
        while(!logged) {
            System.out.print(menu);
            var choice1 = scanner.nextLine();
            int choice;
            try {
                choice = Integer.parseInt(choice1);
            } catch (NumberFormatException e) {
                continue;
            }
            var exit = false;
            switch (choice) {
                case 1 -> {
                    if (userManager.login() != null) {
                        logged = true;
                    }
                }
                case 2 -> userManager.register();
                case 3 -> userManager.forgetPassword();
                case 4 -> exit = true;
                default -> System.out.println("输入错误！");
            }
            if (exit) {
                break;
            }
        }
    }

    public void showMenu() {
        System.out.print("""
            --------欢迎来到员工管理系统----------
            1.添加员工
            2.删除员工
            3.查找员工
            4.修改员工
            5.清空员工
            6.退出
            请输入您的选择：""");
    }

    public void addStaff() {

    }

    public void delStaff() {

    }

    public void findStaff() {

    }

    public void updateStaff() {

    }

    public void clearStaff() {

    }
}
