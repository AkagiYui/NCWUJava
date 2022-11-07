package com.akagiyui.sa;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // 读入数目m和n。产生m个不重复的算式Equation（加法或减法），放入Exercise中，
        // 使用Printer打印Exercise的习题，打印时每列n个算式。
        Printer.printHeader();

        var scanner = new Scanner(System.in); // 从键盘读入

        // 读入每列显示的算式数目m
        int n;
        System.out.print("请输入每列显示算式数目n：");
        n = scanner.nextInt();

        var exercise = new Exercise((short) 100, "equations.txt");
        if (exercise.loadEquations()) {
            // 从文件读入习题并显示
            Printer.printEquations(exercise, n);
            Printer.printEquationsWithResults(exercise, n);
        }

        // 输入新生成的习题数目m
        int m;
        System.out.print("请输入算式数目m：");
        m = scanner.nextInt();
        exercise.recreateEquation(m);

        // 使用Printer打印Exercise的习题，打印时每列n个算式
        Printer.printEquations(exercise, n);
        Printer.printEquationsWithResults(exercise, n);
    }
}
