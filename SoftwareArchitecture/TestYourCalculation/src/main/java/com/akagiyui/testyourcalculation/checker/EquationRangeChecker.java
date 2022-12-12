package com.akagiyui.testyourcalculation.checker;

import com.akagiyui.testyourcalculation.Range;
import com.akagiyui.testyourcalculation.equation.Equation;
import lombok.RequiredArgsConstructor;

/**
 * 算式范围检查器类
 */
@RequiredArgsConstructor
public class EquationRangeChecker implements EquationChecker {
    /**
     * 算式范围
     */
    private final Range range;

    /**
     * 检查算式是否在范围内
     * @param equation 算式
     * @return 是否在范围内
     */
    @Override
    public boolean check(Equation equation) {
        return range.contains(equation.getOperand1())
                && range.contains(equation.getOperand2())
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
