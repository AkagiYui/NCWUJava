package com.akagiyui.sa;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

/**
 * 习题类
 */
public class Exercise implements Iterator<Equation>, Iterable<Equation> {
    private static final int MAX_OPERAND = 100; // 操作数最大值

    private final HashSet<Equation> equations = new HashSet<>(); // 等式集合
    private Equation[] equationsArray; // 等式数组

    /**
     * 生成count个不重复的算式Equation（加法或减法）
     * @param count 欲生成的算式数目
     */
    public void recreateEquation(int count) {
        Equation equation;
        equations.clear();
        for (int i = 0; i < count; i++) {
            do {
                equation = generateEquation();
            } while (!equations.add(equation));
        }
        equationsArray = equations.toArray(Equation[]::new); // 将HashSet转换为数组
    }

    /**
     * 获取等式的数目
     * @return 等式数目
     */
    public int getSize() {
        return equations.size();
    }

    /**
     * 随机生成一个算式
     * @return 算式对象
     */
    private Equation generateEquation() {
        var random = new Random();
        // 如此生成数据可保证各个数都在要求范围内
        if (random.nextBoolean()) {
            var result = (short) random.nextInt(MAX_OPERAND + 1);
            var operand1 = (short) random.nextInt(result + 1);
            var operand2 = (short) (result - operand1);
            return new AddEquation(operand1, operand2);
        } else {
            var operand1 = (short) random.nextInt(MAX_OPERAND + 1);
            var operand2 = (short) random.nextInt(operand1 + 1);
            return new SubEquation(operand1, operand2);
        }
    }

    // 以下为习题类实现迭代功能
    private int index = 0;

    @Override
    public boolean hasNext() {
        var result = index < equations.size();
        if (!result) {
            index = 0;
        }
        return result;
    }

    @Override
    public Equation next() {
        return equationsArray[index++];
    }

    @Override
    public Iterator<Equation> iterator() {
        return equations.iterator();
    }
}
