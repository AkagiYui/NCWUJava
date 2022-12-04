package com.akagiyui.testyourcalculation.checker;

import com.akagiyui.testyourcalculation.equation.Equation;

/**
 * 算式检查器接口
 */
public interface EquationChecker {
    /**
     * 检查算式是否符合规定
     * @param equation 算式
     * @return 是否符合规定
     */
    boolean check(Equation equation);
}
