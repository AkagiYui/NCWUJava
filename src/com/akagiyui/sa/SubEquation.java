package com.akagiyui.sa;

/**
 * 减法算式类
 */
public class SubEquation extends BaseEquation {
    public static final OperatorEnum OPERATOR = OperatorEnum.SUB;

    public SubEquation(short operand1, short operand2) {
        super(operand1, operand2, OPERATOR);
    }

    @Override
    public short calculate() {
        return (short) (getOperand1() - getOperand2());
    }
}
