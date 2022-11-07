package com.akagiyui.sa;

/**
 * 减法算式类
 */
public class SubEquation extends Equation {
    private static final Operator OPERATOR = Operator.SUB;

    public SubEquation(short operand1, short operand2) {
        super(operand1, operand2, OPERATOR);
    }

    @Override
    public short calculate() {
        return (short) (operand1 - operand2);
    }
}
