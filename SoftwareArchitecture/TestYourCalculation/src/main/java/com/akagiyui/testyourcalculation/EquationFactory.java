package com.akagiyui.testyourcalculation;

import com.akagiyui.testyourcalculation.equation.AddEquation;
import com.akagiyui.testyourcalculation.equation.Equation;
import com.akagiyui.testyourcalculation.equation.SubEquation;

/**
 * 算式工厂类
 */
public class EquationFactory {
    /**
     * 获取算式
     * @param type 算式类型
     * @return 算式
     */
    public static Equation getEquation(String type) {
        Equation equation;
        switch (type) {
            case "add" -> equation = new AddEquation();
            case "sub" -> equation = new SubEquation();
            default -> throw new IllegalArgumentException("Invalid type: " + type);
        }
        return equation;
    }
}
