package com.akagiyui.softwaretest;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("请输入三角形的三条边长度: ");
        int a = scan.nextInt();
        int b = scan.nextInt();
        int c = scan.nextInt();
        System.out.println(getTriangleType(a, b, c));
    }

    public static String getTriangleType(int a, int b, int c) {
        if (a <= 0 || b <= 0 || c <= 0) {
            return "非法输入";
        }
        if (a + b <= c || a + c <= b || b + c <= a) {
            return "非法输入";
        }
        if (a == b && b == c) {
            return "等边三角形";
        }
        if (a == b || b == c || a == c) {
            return "等腰三角形";
        }
        return "一般三角形";
    }
}
