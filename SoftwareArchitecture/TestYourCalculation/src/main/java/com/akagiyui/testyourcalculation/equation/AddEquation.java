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

    public AddEquation() {
        super();
        super.setOperator(OPERATOR);
    }

    /**
     * 有参构造方法
     * @param operand1 操作数1
     * @param operand2 操作数2
     */
    public AddEquation(short operand1, short operand2) {
        super(operand1, operand2, OPERATOR);
    }

    @Override
    public short calculate() {
        return (short)(getOperand1() + getOperand2());
    }

    /**
     * 加法算式构造器
     */
    @Setter
    @Accessors(chain = true)
    public static class AddEquationBuilder {
        private short operand1;
        private short operand2;

        /**
         * 构造加法算式
         * @return 加法算式
         */
        public AddEquation buildAddEquation() {
            var equation = EquationFactory.getEquation("add");
            equation.setOperand1(operand1);
            equation.setOperand2(operand2);
            return (AddEquation) equation;
        }
    }
}
