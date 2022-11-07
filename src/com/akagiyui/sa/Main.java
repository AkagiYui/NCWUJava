package com.akagiyui.sa;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // 读入数目m和n。产生m个不重复的算式Equation（加法或减法），放入Exercise中，
        // 使用Printer打印Exercise的习题，打印时每列n个算式。
        Printer.printHeader();

        // 读入数目m和n
        int m;
        int n;
        var scanner = new Scanner(System.in);
        System.out.print("请输入算式数目m：");
        m = scanner.nextInt();
        System.out.print("请输入每列算式数目n：");
        n = scanner.nextInt();

        // 产生m个不重复的算式Equation（加法或减法），放入Exercise中
        var exercise = new Exercise();
        exercise.recreateEquation(m);

        // 使用Printer打印Exercise的习题，打印时每列n个算式
        Printer.printEquations(exercise, n);
        Printer.printEquationsWithResults(exercise, n);
    }
}
