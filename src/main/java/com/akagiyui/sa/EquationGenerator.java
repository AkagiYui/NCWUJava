package main.java.com.akagiyui.sa;

import java.util.*;

/**
 * 算式生成器，单例模式，迭代器模式
 */
public class EquationGenerator implements Iterator<Equation>, Iterable<Equation>  {
    /**
     * 实例保持器
     */
    private static class SingletonHolder {
        private static final EquationGenerator INSTANCE = new EquationGenerator();
    }

    /**
     * 私有化构造方法
     */
    private EquationGenerator() {}

    /**
     * 获取实例
     * @return 实例
     */
    public static EquationGenerator getInstance() {
        return SingletonHolder.INSTANCE;
    }

    // 算式集合
    private static ArrayList<Equation> equations = new ArrayList<>();

    /**
     * 生成多个算式
     * @param n 算式数目
     * @param checker 算式范围检查器
     */
    public void generate(int n, EquationRangeChecker checker) {
        Set<Equation> equations = new HashSet<>();
        while (equations.size() < n) {
            var equation = generateEquation(checker.getRange());
            if (checker.check(equation)) {
                equations.add(equation);
            }
        }
        EquationGenerator.equations = new ArrayList<>(equations);
    }

    /**
     * 随机生成一个算式
     * @return 算式对象
     */
    private Equation generateEquation(Range range) {
        var random = new Random();
        var addEquationBuilder = new AddEquation.AddEquationBuilder();
        var subEquationBuilder = new SubEquation.SubEquationBuilder();
        // 如此生成数据可保证各个数都在要求范围内
        if (random.nextBoolean()) {
            var result = random.nextInt(range.getStart(), range.getEnd() + 1);
            var operand1 = (short)random.nextInt(range.getStart(), result + 1);
            var operand2 = (short)(result - operand1);
            return addEquationBuilder
                    .setOperand1(operand1)
                    .setOperand2(operand2)
                    .buildAddEquation();
        } else {
            var operand1 = (short)random.nextInt(range.getStart(), range.getEnd() + 1);
            var operand2 = (short)random.nextInt(range.getStart(), operand1 + 1);
            subEquationBuilder.setOperand1(operand1);
            subEquationBuilder.setOperand2(operand2);
            return subEquationBuilder
                    .setOperand1(operand1)
                    .setOperand2(operand2)
                    .buildSubEquation();
        }
    }

    // 以下部分为EquationGenerator实现迭代器模式
    private int index = 0;

    @Override
    public boolean hasNext() {
        var result = index < EquationGenerator.equations.size();
        if (!result) {
            index = 0;
        }
        return result;
    }

    @Override
    public Equation next() {
        return EquationGenerator.equations.get(index++);
    }

    @Override
    public Iterator<Equation> iterator() {
        return EquationGenerator.equations.iterator();
    }
}
