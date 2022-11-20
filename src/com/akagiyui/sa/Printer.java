package com.akagiyui.sa;

/**
 * 打印类
 */
public class Printer {
    /**
     * 打印功能描述
     */
    public static void printHeader() {
        System.out.println(
            """
            本程序作用：
            1. 产生多个个算数式
            2. 显示该批算数式
            3. 显示该批算术式结果
            """
        );
    }

    /**
     * 打印算式
     * @param exercise 习题类
     * @param countPerColumn 每列算式数目
     * @param showResult 是否显示结果
     */
    public static void printEquations(Exercise exercise, int countPerColumn, boolean showResult) {
        var stringResult = new StringBuilder(); // 用于存储输出文本
        int countPerRow = exercise.getSize() / countPerColumn; // 每行算式数目
        var countRemain = exercise.getSize() % countPerColumn; // 剩余算式数目

        var countThisRow = 0; // 当前行已输出算式数目
        var stringThisRow = new StringBuilder(); // 当前行输出文本
        var addedThisRow = false; // 当前行是否已添加剩余的算式（仅当countRemain > 0时有效）
        for (var equation : exercise) {
            stringThisRow.append(getEquationString(equation, showResult)).append("\t\t"); // 输出算式
            countThisRow++; // 当前行已输出算式数目+1
            if (countThisRow == countPerRow) { // 当前行已输出countPerRow个算式
                if (countRemain > 0 && !addedThisRow) { // 剩余算式数目大于0且当前行未添加剩余的算式
                    countRemain--; // 剩余算式数目-1
                    countThisRow--; // 当前行已输出算式数目-1
                    addedThisRow = true; // 当前行已添加剩余的算式
                    continue;
                }
                stringResult.append(stringThisRow).append("\n"); // 输出当前行
                countThisRow = 0;
                stringThisRow = new StringBuilder();
                addedThisRow = false;
            }
        }

        System.out.println("算数式" + (showResult ? "结果" : "") + "：");
        System.out.println(stringResult);
    }

    /**
     * 打印算式
     * @param exercise 习题类
     * @param countPerColumn 每列算式数目
     */
    public static void printEquations(Exercise exercise, int countPerColumn) {
        printEquations(exercise, countPerColumn, false);
    }

    /**
     * 打印算式和结果
     * @param exercise 习题类
     * @param countPerColumn 每列算式数目
     */
    public static void printEquationsWithResults(Exercise exercise, int countPerColumn) {
        printEquations(exercise, countPerColumn, true);
    }

    /**
     * 打印算式
     * @param equation 算式
     * @param showResult 是否显示结果
     */
    private static String getEquationString(Equation equation, boolean showResult) {
        var equationString = new StringBuilder();
        equationString.append(equation.toString());
        if (showResult) {
            equationString.append(equation.calculate());
        }
        return equationString.toString();
    }

    /**
     * 打印对象
     * @param object 欲打印的对象
     */
    public static void printObject(Object object) {
        try {
            System.out.println(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
