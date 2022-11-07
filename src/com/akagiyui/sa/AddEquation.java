package com.akagiyui.sa;

/**
 * 加法算式类
 */
public class AddEquation extends Equation {
    private static final Operator OPERATOR = Operator.ADD;

    public AddEquation(short operand1, short operand2) {
        super(operand1, operand2, OPERATOR);
    }

    @Override
    public short calculate() {
        return (short) (operand1 + operand2);
    }
}
