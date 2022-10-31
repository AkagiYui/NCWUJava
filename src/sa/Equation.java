package sa;

/**
 * 等式类
 */
public abstract class Equation {
    public short operand1; // 操作数1
    public short operand2; // 操作数2
    public char operator; // 运算符

    public Equation(short operand1, short operand2, char operator) {
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.operator = operator;
    }

    /**
     * 计算结果
     * @return 结果
     */
    public abstract short calculate();

    /**
     * 重写equals方法，用于输出算式
     * @return 算式
     */
    @Override
    public String toString() {
        return operand1 + "\t" + operator + "\t" + operand2 + "\t=\t";
    }

    /**
     * 重写equals方法，用于判断两个等式是否相等
     * @param obj 另一个等式
     * @return 是否相等
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Equation equation) {
            var result = (operand1 == equation.operand1);
            result &= (operand2 == equation.operand2);
            result &= (operator == equation.operator);
            return result;
        }
        return false;
    }

    /**
     * 重写hashCode方法，使得两个等式对象的hashCode相等，才能放入HashSet中
     * @return hashCode
     */
    @Override
    public int hashCode() {
        return operand1 + operand2 + operator;
    }
}
