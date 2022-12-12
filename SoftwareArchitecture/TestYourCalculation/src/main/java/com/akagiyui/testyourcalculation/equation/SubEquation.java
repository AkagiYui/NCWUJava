package com.akagiyui.testyourcalculation.equation;

import com.akagiyui.testyourcalculation.EquationFactory;
import com.akagiyui.testyourcalculation.Operator;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 减法算式类
 */
public class SubEquation extends Equation {
    public static final Operator OPERATOR = Operator.SUB;

    public SubEquation() {
        super();
        super.setOperator(OPERATOR);
    }

    /**
     * 有参构造方法
     * @param operand1 操作数1
     * @param operand2 操作数2
     */
    public SubEquation(int operand1, int operand2) {
        super(operand1, operand2, OPERATOR);
    }

    @Override
    public int calculate() {
        return getOperand1() - getOperand2();
    }

    /**
     * 减法算式构造器
     */
    @Setter
    @Accessors(chain = true) // 使得 setter 方法返回当前对象，以支持链式调用
    public static class SubEquationBuilder {
        private int operand1;
        private int operand2;

        /**
         * 构造减法算式
         * @return 减法算式
         */
        public SubEquation build() {
            var equation = EquationFactory.getEquation("sub");
            equation.setOperand1(operand1);
            equation.setOperand2(operand2);
            return (SubEquation) equation;
        }
    }
}
