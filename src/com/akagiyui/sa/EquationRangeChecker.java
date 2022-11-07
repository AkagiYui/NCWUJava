package com.akagiyui.sa;

/**
 * 算式范围检查器类
 */
public class EquationRangeChecker implements EquationChecker {
    private final Range range; // 范围

    public EquationRangeChecker(short min, short max) {
        range = new Range(min, max);
    }

    /**
     * 检查算式是否在范围内
     * @param equation 算式
     * @return 是否在范围内
     */
    @Override
    public boolean check(Equation equation) {
        return equation.calculate() >= range.start() && equation.calculate() <= range.end()
                && equation.operand1 >= range.start() && equation.operand1 <= range.end()
                && equation.operand2 >= range.start() && equation.operand2 <= range.end();
    }

    /**
     * 获取范围
     * @return 范围
     */
    public Range getRange() {
        return range;
    }
}
