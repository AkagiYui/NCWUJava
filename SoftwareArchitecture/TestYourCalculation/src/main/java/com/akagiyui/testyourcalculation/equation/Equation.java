package com.akagiyui.testyourcalculation.equation;

import com.akagiyui.testyourcalculation.Operator;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
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
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "operator") // 序列化时使用类名
@JsonSubTypes({
        @JsonSubTypes.Type(value = AddEquation.class, name = "ADD"),
        @JsonSubTypes.Type(value = SubEquation.class, name = "SUB")
})
public abstract class Equation implements Serializable {
    /**
     * 操作数1
     */
    private int operand1;
    /**
     * 操作数2
     */
    private int operand2;
    /**
     * 运算符
     */
    private Operator operator; // 运算符

    /**
     * 计算结果
     * @return 结果
     */
    public abstract int calculate();

    /**
     * 以文本格式输出算式题目
     * @return 算式
     */
    @Override
    public String toString() {
        return operand1 + " " + operator.getOperator() + " " + operand2 + " = ";
    }
}
