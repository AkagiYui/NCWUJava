package com.akagiyui.sa;

import java.util.*;

/**
 * 习题类
 */
public class Exercise implements Iterator<BaseEquation>, Iterable<BaseEquation> {
    private final short max_operand; // 操作数最大值
    private final String filename; // 习题文件名

    private List<BaseEquation> equations = new ArrayList<>(); // 算式集合
    private final EquationGeneratorSingleton generator = EquationGeneratorSingleton.INSTANCE; // 算式生成器

    public Exercise(short max_operand, String filename) {
        this.max_operand = max_operand;
        this.filename = filename;
    }

    /**
     * 生成count个不重复的算式Equation（加法或减法）
     * @param count 欲生成的算式数目
     */
    public void recreateEquation(int count) {
        var checker = new EquationRangeChecker((short) 0, max_operand);
        equations = generator.generate(count, checker);
        saveEquations();
    }

    /**
     * 获取算式的数目
     * @return 算式数目
     */
    public int getSize() {
        return equations.size();
    }

    /**
     * 保存算式集合到文件
     */
    private void saveEquations() {
        if (Utils.saveObject(equations, filename)) {
            System.out.println("习题已保存到文件。" + filename);
        } else {
            System.out.println("习题保存失败。");
        }
    }

    /**
     * 从文件中读取算式集合
     * @return 是否成功
     */
    public boolean loadEquations() {
        ArrayList<BaseEquation> equations = Utils.loadObjectOrNull(filename);
        if (equations != null && !equations.isEmpty()) {
            this.equations = equations;
            System.out.println("习题已从文件" + filename + "读入。");
            return true;
        } else {
            System.out.println("习题读入失败。");
            return false;
        }
    }

    // 以下部分为Exercise类实现迭代功能
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
    public BaseEquation next() {
        return equations.get(index++);
    }

    @Override
    public Iterator<BaseEquation> iterator() {
        return equations.iterator();
    }
}
