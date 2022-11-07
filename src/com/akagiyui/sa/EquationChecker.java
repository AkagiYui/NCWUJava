package com.akagiyui.sa;

/**
 * 算式检查器接口
 */
public interface EquationChecker {
    /**
     * 检查算式是否正确
     * @param equation 算式
     * @return 是否正确
     */
    boolean check(Equation equation);
}
