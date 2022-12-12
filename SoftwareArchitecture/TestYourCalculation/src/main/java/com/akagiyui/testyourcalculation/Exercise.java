package com.akagiyui.testyourcalculation;

import com.akagiyui.testyourcalculation.checker.EquationRangeChecker;
import com.akagiyui.testyourcalculation.equation.Equation;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * 习题类
 */
public class Exercise implements Iterator<Equation>, Iterable<Equation> {
    /**
     * 操作数最大值
     */
    private final int max_operand;
    /**
     * 算式集合
     */
    private final List<Equation> equations = new ArrayList<>();
    /**
     * 算式生成器
     */
    private final EquationGenerator generator = EquationGenerator.getInstance();

    /**
     * 习题
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
        var checker = new EquationRangeChecker(new Range(0, max_operand));
        generator.generate(count, checker);
        equations.clear();
        generator.forEach(equations::add);
    }

    public void recreateEquation() {
        recreateEquation(equations.size());
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

    /**
     * 从文件中读取习题
     * @param file 文件
     * @return 是否读取成功
     */
    public boolean load(File file) {
        try {
            var mapper = new ObjectMapper();
            var equations = mapper.readValue(file, Equation[].class);
            this.equations.clear();
            Collections.addAll(this.equations, equations);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 保存习题到文件
     * @param file 文件
     * @return 是否保存成功
     */
    public boolean save(File file) {
        var mapper = new ObjectMapper();
        try {
            mapper.writeValue(file, equations.toArray());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // 以下部分为Exercise类实现迭代功能
    private int index = 0;

    @Override
    public boolean hasNext() {
        var r = index < equations.size();
        if (!r) {
            index = 0;
        }
        return r;
    }

    @Override
    public Equation next() {
        var equation = equations.get(index);
        index++;
        return equation;
    }

    @Override
    public Iterator<Equation> iterator() {
        return equations.iterator();
    }
}
