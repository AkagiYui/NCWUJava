package com.akagiyui;

import javax.swing.*;

/**
 * 程序入口
 */
public class Main extends JFrame {
    public Main() {
        super("Test Your Calculation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 关闭窗口时退出程序
        setResizable(false); // 窗口大小不可变
        setLocationRelativeTo(null); // 设置窗口居中显示
        add(new Panel()); // 添加面板
        pack(); // 自动调整窗口大小
        setVisible(true); // 显示窗口
    }

    public static void main(String[] args) {
        new Main();
    }
}
