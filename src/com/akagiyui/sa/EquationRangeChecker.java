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
        return range.contains(equation.operand1)
                && range.contains(equation.operand2)
                && range.contains(equation.calculate());
    }

    /**
     * 获取范围
     * @return 范围
     */
    public Range getRange() {
        return range;
    }
}
