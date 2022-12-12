package com.akagiyui.testyourcalculation.equation;

import com.akagiyui.testyourcalculation.EquationFactory;
import com.akagiyui.testyourcalculation.Operator;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 加法算式类
 */
public class AddEquation extends Equation {
    public static final Operator OPERATOR = Operator.ADD;

    /**
     * 加法算式
     */
    public AddEquation() {
        super();
        super.setOperator(OPERATOR);
    }

    /**
     * 加法算式
     * @param operand1 操作数1
     * @param operand2 操作数2
     */
    public AddEquation(int operand1, int operand2) {
        super(operand1, operand2, OPERATOR);
    }

    @Override
    public int calculate() {
        return getOperand1() + getOperand2();
    }

    /**
     * 加法算式构造器
     */
    @Setter
    @Accessors(chain = true) // 使得 setter 方法返回当前对象，以支持链式调用
    public static class AddEquationBuilder {
        private int operand1;
        private int operand2;

        /**
         * 构造加法算式
         * @return 加法算式
         */
        public AddEquation build() {
            var equation = EquationFactory.getEquation("add");
            equation.setOperand1(operand1);
            equation.setOperand2(operand2);
            return (AddEquation) equation;
        }
    }
}
