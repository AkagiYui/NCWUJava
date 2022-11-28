package sixth;

// 有五个学生，每个学生有3门课的成绩，从键盘输入以上数据（包括学生号，姓名，三门课成绩），计算出平均成绩，
// 把原有的数据和计算出的平均分数存放在磁盘文件 "stud.txt "中。

import java.io.File;
import java.io.FileWriter;

public class Main {
    public static void main(String[] args) {
        var scanner = new java.util.Scanner(System.in);
        var students = new Student[5];
        for (int i = 0; i < 5; i++) {
            System.out.print("请输入学生的学号：");
            var id = scanner.nextLine();
            System.out.print("请输入学生的姓名：");
            var name = scanner.nextLine();
            var scores = new int[3];
            for (int j = 0; j < 3; j++) {
                System.out.print("请输入学生的第" + (j + 1) + "门课成绩：");
                var score = scanner.nextLine();
                try {
                    scores[j] = Integer.parseInt(score);
                } catch (NumberFormatException e) {
                    System.out.println("输入的成绩不是数字，已自动设置为0");
                }
            }
            students[i] = new Student(id, name, scores);
            System.out.println("\n");
        }

        // save as text
        var savedString = new StringBuilder();
        for (var student : students) {
            savedString.append(student.getId()).append(" ").append(student.getName()).append("\n");
            for (var score : student.getScores()) {
                savedString.append(score).append(" ");
            }
            var average = 0.0;
            for (var score : student.getScores()) {
                average += score;
            }
            average /= student.getScores().length;
            savedString.append(average).append("\n\n");
        }

        File file = new File("stud.txt");
        try {
            var fileWriter = new FileWriter(file);
            fileWriter.write(savedString.toString());
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
