package com.akagiyui.sa;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException{
        // 使用反射查询 AddEquation 类的字段和方法，并使用无参构造创建对象
        System.out.println("使用反射查询 AddEquation 类的字段和方法，并使用无参构造创建对象");
        Class<?> clazz = Class.forName("com.akagiyui.sa.AddEquation");
        System.out.println("类名：" + clazz.getName());
        System.out.println("字段：");
        for (var field : clazz.getDeclaredFields()) {
            System.out.println(field);
        }
        System.out.println("方法：");
        for (var method : clazz.getDeclaredMethods()) {
            System.out.println(method);
        }
        System.out.println("构造方法：");
        for (var constructor : clazz.getDeclaredConstructors()) {
            System.out.println(constructor);
        }
        System.out.println("创建对象：");
        Object obj;
        try {
            obj = clazz.getDeclaredConstructor().newInstance();
            if (obj instanceof Equation equation) {
                equation.setOperand1((short)1);
                equation.setOperand2((short)2);
                equation.setOperator(Operator.ADD);
                System.out.println(equation + "" + equation.calculate());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        // 读入数目m和n。产生m个不重复的算式Equation（加法或减法），放入Exercise中，
        // 使用Printer打印Exercise的习题，打印时每列n个算式。
        Printer.printHeader();

        var scanner = new Scanner(System.in); // 从键盘读入

        // 读入每列显示的算式数目m
        int n;
        System.out.print("请输入每列显示算式数目n：");
        n = scanner.nextInt();

        var exercise = new Exercise((short)100, "equations.dat");
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

        // 打印JSON
        Printer.printObject(exercise.toJSON(true));
    }
}
