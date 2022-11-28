package com.akagiyui;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;

/**
 * 习题类
 */
public class Exercise implements Iterator<Equation>, Iterable<Equation> {
    private final short max_operand; // 操作数最大值
    private final String filename; // 习题文件名

    private List<Equation> equations = new ArrayList<>(); // 算式集合
    private final EquationGenerator generator = EquationGenerator.getInstance(); // 算式生成器

    /**
     * 构造方法
     * @param max_operand 操作数最大值
     * @param filename 习题文件名
     */
    public Exercise(short max_operand, String filename) {
        this.max_operand = max_operand;
        this.filename = filename;
    }

    /**
     * 生成count个不重复的算式Equation（加法或减法）
     * @param count 欲生成的算式数目
     */
    public void recreateEquation(int count) {
        var checker = new EquationRangeChecker((short)0, max_operand);
        generator.generate(count, checker);
        equations.clear();
        for (var equation : generator) {
            equations.add(equation);
        }
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
            System.out.println("习题已保存到文件：" + filename);
        } else {
            System.out.println("习题保存失败。");
        }
    }

    /**
     * 从文件中读取算式集合
     * @return 是否成功
     */
    public boolean loadEquations() {
        ArrayList<Equation> equations = Utils.loadObjectOrNull(filename);
        if (equations != null && !equations.isEmpty()) {
            this.equations = equations;
            System.out.println("习题已从文件 " + filename + " 读入。");
            return true;
        } else {
            System.out.println("习题读入失败。");
            return false;
        }
    }

    /**
     * 把习题集转换为JSON字符串
     * @param pretty 是否美化
     * @return JSON字符串
     */
    public String toJSON(boolean pretty) {
        var mapper = new ObjectMapper();
        String jsonText = null;
        try {
            if (pretty) {
                jsonText = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(equations);
            } else {
                jsonText = mapper.writeValueAsString(equations);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonText;
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
}
