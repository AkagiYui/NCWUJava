package experiment.fifth;

import java.util.Scanner;

public class StaffManagerTest {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        var staffManager = new StaffManager();
        staffManager.init(); //登录
        while (staffManager.isLogged()) {
            staffManager.showMenu(); //显示菜单

            var choice1 = scanner.nextLine();
            int choice;
            try {
                choice = Integer.parseInt(choice1);
            } catch (NumberFormatException e) {
                continue;
            }

            var exit = false;
            switch (choice) {
                case 1 -> staffManager.addStaff();
                case 2 -> staffManager.delStaff();
                case 3 -> staffManager.findStaff();
                case 4 -> staffManager.updateStaff();
                case 5 -> staffManager.clearStaff();
                case 6 -> exit = true;
                default -> System.out.println("输入错误，请重新输入");
            }
            if (exit) {
                break;
            }
        }
        staffManager.saveData();
    }
}
