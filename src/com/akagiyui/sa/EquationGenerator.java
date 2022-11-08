package com.akagiyui.sa;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * 算式生成器，单例模式
 */
public enum EquationGenerator {
    INSTANCE; // 唯一实例

    /**
     * 生成多个算式
     * @param n 算式数目
     * @param checker 算式范围检查器
     * @return 算式集合
     */
    public ArrayList<Equation> generate(int n, EquationRangeChecker checker) {
        Set<Equation> equations = new HashSet<>();
        while (equations.size() < n) {
            var equation = generateEquation(checker.getRange());
            if (checker.check(equation)) {
                equations.add(equation);
            }
        }
        return new ArrayList<>(equations);
    }

    /**
     * 随机生成一个算式
     * @return 算式对象
     */
    private Equation generateEquation(Range range) {
        var random = new Random();
        // 如此生成数据可保证各个数都在要求范围内
        if (random.nextBoolean()) {
            var result = random.nextInt(range.start, range.end + 1);
            var operand1 = (short) random.nextInt(range.start, result + 1);
            var operand2 = (short) (result - operand1);
            return new AddEquation(operand1, operand2);
        } else {
            var operand1 = (short) random.nextInt(range.start, range.end + 1);
            var operand2 = (short) random.nextInt(range.start, operand1 + 1);
            return new SubEquation(operand1, operand2);
        }
    }
}
