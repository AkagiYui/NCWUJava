package sa;

/**
 * 加法等式类
 */
public class AddEquation extends Equation {

    public AddEquation(short operand1, short operand2) {
        super(operand1, operand2, '+');
    }

    @Override
    public short calculate() {
        return (short) (operand1 + operand2);
    }
}
