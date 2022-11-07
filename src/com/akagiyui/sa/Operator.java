package com.akagiyui.sa;

/**
 * 运算符枚举类
 */
public enum Operator {
    ADD('+'),  // 加法
    SUB('-');  // 减法

    private final char operator; // 运算符文本

    Operator(char operator) {
        this.operator = operator;
    }

    public char getOperator() {
        return operator;
    }
}
