package com.akagiyui.sa;

/**
 * 减法等式类
 */
public class SubEquation extends Equation {

    public SubEquation(short operand1, short operand2) {
        super(operand1, operand2, '-');
    }

    @Override
    public short calculate() {
        return (short) (operand1 - operand2);
    }
}
