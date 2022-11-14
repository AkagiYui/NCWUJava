package com.akagiyui.sa;

import java.io.Serializable;

/**
 * 算式类
 */
public abstract class BaseEquation implements Serializable {
    private final short operand1; // 操作数1
    private final short operand2; // 操作数2
    private final OperatorEnum operator; // 运算符

    public short getOperand1() {
        return operand1;
    }

    public short getOperand2() {
        return operand2;
    }

    public OperatorEnum getOperator() {
        return operator;
    }

    public BaseEquation(short operand1, short operand2, OperatorEnum operator) {
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
        if (obj instanceof BaseEquation equation) {
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
        // 要尽量均匀地分布，否则可能会导致HashSet的性能下降
        return operand1 * 1000 + operand2 * 100 + operator.ordinal();
    }
}
