package sa;

import java.util.Random;

// 分离其内在逻辑，分解成若干个模块（函数/方法），
// 包括：程序功能提示、产生10个算数式（随机数）、显示该批算数式、显示该批算术式结果。
// 另外，约束操作数不为负切且不超过100，约束减法结果不能为负，约束加法结果不超过100。
public class BinaryOperation_0 {
    // 主函数
    public static void main(String[] args) {
        printProgramDescription();
        createEquations();
        printQuestions();
        printEquations();
    }


    // 程序功能提示
    public static void printProgramDescription() {
        System.out.println("""
                本程序作用：
                1. 产生10个算数式
                2. 显示该批算数式
                3. 显示该批算术式结果
                """);
    }

    static Equation[] equations = new Equation[10];

    // 产生10个算数式（随机数）
    public static void createEquations() {
        for (int i = 0; i < 10; i++) {
            equations[i] = Equation.generateEquation();
        }
    }

    // 显示该批算数式
    public static void printQuestions() {
        System.out.println("算数式：");
        for (var i = 0; i < equations.length; i++) {
            System.out.println("" + (i + 1) + ":\t" + equations[i]);
        }
    }

    // 显示该批算术式结果
    public static void printEquations() {
        System.out.println("算数式结果：");
        for (var i = 0; i < equations.length; i++) {
            System.out.println("" + (i + 1) + ":\t" + equations[i] + equations[i].result);
        }
    }
}

// 算式类
class Equation {
    short numberA;
    short numberB;
    char operation;
    short result;

    public Equation(short numberA, short numberB, char operation, short result) {
        this.numberA = numberA;
        this.numberB = numberB;
        this.operation = operation;
        this.result = result;
    }

    public static Equation generateEquation() {
        var random = new Random();
        if (random.nextBoolean()) {
            var operation = '+';
            var result = (short) random.nextInt(101);
            var numberA = (short) random.nextInt(result + 1);
            var numberB = (short) (result - numberA);
            return new Equation(numberA, numberB, operation, result);
        } else {
            var operation = '-';
            var numberA = (short) random.nextInt(101);
            var numberB = (short) random.nextInt(numberA + 1);
            var result = (short) (numberA - numberB);
            return new Equation(numberA, numberB, operation, result);
        }
    }

    @Override
    public String toString() {
        return numberA + " " + operation + " " + numberB + " = ";
    }
}