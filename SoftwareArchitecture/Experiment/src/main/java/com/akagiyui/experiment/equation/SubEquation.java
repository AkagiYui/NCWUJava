package com.akagiyui.experiment.equation;

import com.akagiyui.experiment.EquationFactory;
import com.akagiyui.experiment.Operator;
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
    public SubEquation(short operand1, short operand2) {
        super(operand1, operand2, OPERATOR);
    }

    @Override
    public short calculate() {
        return (short)(getOperand1() - getOperand2());
    }

    /**
     * 减法算式构造器
     */
    @Setter
    @Accessors(chain = true)
    public static class SubEquationBuilder {
        private short operand1;
        private short operand2;

        /**
         * 构造减法算式
         * @return 减法算式
         */
        public SubEquation buildSubEquation() {
            var equation = EquationFactory.getEquation("sub");
            equation.setOperand1(operand1);
            equation.setOperand2(operand2);
            return (SubEquation) equation;
        }
    }
}
