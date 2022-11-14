package com.akagiyui.sa;

/**
 * 运算符枚举类
 */
public enum OperatorEnum {
    ADD('+'),  // 加法
    SUB('-');  // 减法

    private final char operator; // 运算符文本

    OperatorEnum(char operator) {
        this.operator = operator;
    }

    public char getOperator() {
        return operator;
    }
}
