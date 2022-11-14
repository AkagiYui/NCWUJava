package com.akagiyui.sa;

/**
 * 加法算式类
 */
public class AddEquation extends BaseEquation {
    public static final OperatorEnum OPERATOR = OperatorEnum.ADD;

    public AddEquation(short operand1, short operand2) {
        super(operand1, operand2, OPERATOR);
    }

    @Override
    public short calculate() {
        return (short) (getOperand1() + getOperand2());
    }
}
