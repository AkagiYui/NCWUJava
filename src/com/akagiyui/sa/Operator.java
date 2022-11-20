package com.akagiyui.sa;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 运算符枚举类
 */
@Getter
@RequiredArgsConstructor // 生成全参构造
public enum Operator {
    ADD('+'),  // 加法
    SUB('-');  // 减法

    private final char operator; // 运算符文本
}
