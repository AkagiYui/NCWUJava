package com.akagiyui.testyourcalculation;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 运算符枚举类
 * <p>
 * TODO 加入更多运算符
 */
@Getter
@RequiredArgsConstructor // 生成全参构造方法
public enum Operator {
    /**
     * 加号
     */
    ADD('+'),
    /**
     * 减号
     */
    SUB('-');

    /**
     * 运算符字符
     */
    private final char operator;
}
