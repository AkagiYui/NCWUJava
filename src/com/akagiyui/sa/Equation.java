package com.akagiyui.sa;

import java.io.Serializable;

/**
 * 算式类
 */
public abstract class Equation implements Serializable {
    public final short operand1; // 操作数1
    public final short operand2; // 操作数2
    public final Operator operator; // 运算符

    public Equation(short operand1, short operand2, Operator operator) {
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.operator = operator;
    }

    /**
     * 计算结果
     * @return 结果
     */
    public abstract short calculate();

    /**
     * 重写toString方法，用于输出算式
     * @return 算式
     */
    @Override
    public String toString() {
        return operand1 + "\t" + operator.getOperator() + "\t" + operand2 + "\t=\t";
    }

    /**
     * 重写equals方法，用于判断两个算式是否相等
     * @param obj 一个对象
     * @return 是否相等
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Equation equation) {
            var result = (operand1 == equation.operand1);
            result &= (operand2 == equation.operand2);
            result &= (operator == equation.operator);
            return result;
        }
        return false;
    }

    /**
     * 重写hashCode方法，使得两个算式对象的hashCode相等，才能防止被重复放入HashSet中
     * @return hashCode
     */
    @Override
    public int hashCode() {
        return operand1 + operator.getOperator() + operand2;
    }
}
