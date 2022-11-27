package main.java.com.akagiyui.foundation.experiment.fifth;

import main.java.com.akagiyui.foundation.experiment.fifth.entity.Staff;

import java.util.HashMap;
import java.util.Scanner;

public class StaffManager {
    private final HashMap<String, Staff> staffs; // 员工列表
    private final UserManager userManager = new UserManager(); // 用户管理
    private boolean logged = false; // 已登录
    private final Scanner scanner = new Scanner(System.in);

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

    public void saveData() {
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
        System.out.println("===添加员工===");
        var staff = new Staff();

        while (staff.getId() == null) {
            System.out.print("请输入员工id：");
            var id = scanner.nextLine();
            if (staffs.containsKey(id)) {
                System.out.println("员工id已存在！");
            } else {
                staff.setId(id);
            }
        }

        while (staff.getName() == null) {
            System.out.print("请输入员工姓名：");
            var name = scanner.nextLine();
            if (name.length() == 0) {
                System.out.println("员工姓名不能为空！");
            } else {
                staff.setName(name);
            }
        }

        while (staff.getAge() == 0) {
            System.out.print("请输入员工年龄：");
            var age = scanner.nextLine();
            try {
                var age1 = Integer.parseInt(age);
                if (age1 < 0) {
                    System.out.println("员工年龄不能小于0！");
                } else {
                    staff.setAge(age1);
                }
            } catch (NumberFormatException e) {
                System.out.println("员工年龄必须为整数！");
            }
        }

        while (staff.getAddress() == null) {
            System.out.print("请输入员工地址：");
            var address = scanner.nextLine();
            if (address.length() == 0) {
                System.out.println("员工地址不能为空！");
            } else {
                staff.setAddress(address);
            }
        }

        staffs.put(staff.getId(), staff);
        saveData();
        System.out.println("员工添加成功！");
    }

    public void delStaff() {
        System.out.println("===删除员工===");
        System.out.print("请输入员工id：");
        var id = scanner.nextLine();
        if (staffs.containsKey(id)) {
            staffs.remove(id);
            saveData();
            System.out.println("员工删除成功！");
        } else {
            System.out.println("员工id不存在！");
        }
    }

    public void findStaff() {
        // list all staffs
        System.out.println("===查找员工===");
        if (staffs.isEmpty()) {
            System.out.println("当前无员工信息，请添加后再查询！");
            return;
        }
        for (var staff : staffs.values()) {
            System.out.println("员工id：" + staff.getId());
            System.out.println("员工姓名：" + staff.getName());
            System.out.println("员工年龄：" + staff.getAge());
            System.out.println("员工地址：" + staff.getAddress());
            System.out.println();
        }
        scanner.nextLine();
    }

    public void updateStaff() {
        System.out.println("===修改员工===");
        System.out.print("请输入员工id：");
        var id = scanner.nextLine();
        if (staffs.containsKey(id)) {
            var staff = staffs.get(id);
            System.out.print("请输入员工姓名：");
            var name = scanner.nextLine();
            if (name.length() != 0) {
                staff.setName(name);
            }
            System.out.print("请输入员工年龄：");
            var age = scanner.nextLine();
            try {
                var age1 = Integer.parseInt(age);
                if (age1 >= 0) {
                    staff.setAge(age1);
                }
            } catch (NumberFormatException ignored) {
            }
            System.out.print("请输入员工地址：");
            var address = scanner.nextLine();
            if (address.length() != 0) {
                staff.setAddress(address);
            }
            saveData();
            System.out.println("员工修改成功！");
        } else {
            System.out.println("员工id不存在！");
        }
    }

    public void clearStaff() {
        System.out.println("===清空员工===");
        System.out.println("确定要清空所有员工吗？(y/n)");
        var choice = scanner.nextLine();
        if (choice.equals("y")) {
            staffs.clear();
            saveData();
            System.out.println("员工清空成功！");
        }
    }
}
