package com.akagiyui.testyourcalculation.equation;

import com.akagiyui.testyourcalculation.Operator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 算式类
 */
@Data // 自动实现 getter/setter/toString/equals/hashCode
@NoArgsConstructor // 无参构造
@AllArgsConstructor // 全参构造
public abstract class Equation implements Serializable {
    private int operand1; // 操作数1
    private int operand2; // 操作数2
    private Operator operator; // 运算符

    /**
     * 计算结果
     * @return 结果
     */
    public abstract int calculate();

    /**
     * 重写toString方法，用于输出算式
     * @return 算式
     */
    @Override
    public String toString() {
        return operand1 + " " + operator.getOperator() + " " + operand2 + " = ";
    }
}
