package com.akagiyui.testyourcalculation;

import com.akagiyui.testyourcalculation.checker.EquationRangeChecker;
import com.akagiyui.testyourcalculation.equation.Equation;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 习题类
 */
public class Exercise implements Iterator<Equation>, Iterable<Equation> {
    private final int max_operand; // 操作数最大值
    private int count;
    private final List<Equation> equations = new ArrayList<>(); // 算式集合
    private final EquationGenerator generator = EquationGenerator.getInstance(); // 算式生成器

    /**
     * 构造方法
     * @param max_operand 操作数最大值
     */
    public Exercise(int max_operand) {
        this.max_operand = max_operand;
    }

    /**
     * 生成count个不重复的算式Equation（加法或减法）
     * @param count 欲生成的算式数目
     */
    public void recreateEquation(int count) {
        var checker = new EquationRangeChecker(0, max_operand);
        generator.generate(count, checker);
        this.count = count;
        equations.clear();
        for (var equation : generator) {
            equations.add(equation);
        }
    }

    public void recreateEquation() {
        recreateEquation(this.count);
    }

    /**
     * 获取算式
     * @param index 算式索引
     * @return 算式
     */
    public Equation getEquation(int index) {
        return equations.get(index);
    }

    /**
     * 获取算式的数目
     * @return 算式数目
     */
    public int getSize() {
        return equations.size();
    }

    public boolean loadEquations(File f) {
        return false;
    }


    // 以下部分为Exercise类实现迭代功能
    @Override
    public boolean hasNext() {
        return generator.hasNext();
    }

    @Override
    public Equation next() {
        return generator.next();
    }

    @Override
    public Iterator<Equation> iterator() {
        return generator.iterator();
    }

    public boolean save(File file) {
        var mapper = new ObjectMapper();
        try {
            mapper.writeValue(file, equations);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
