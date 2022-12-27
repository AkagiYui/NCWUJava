package com.akagiyui.testyourcalculation.equation;

import com.akagiyui.testyourcalculation.Operator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EquationTest {
    static class TestEquation extends Equation {
        @Override
        public int calculate() {
            return 0;
        }
    }

    @Test
    void calculate() {
        Equation equation = new TestEquation();
        assertEquals(0, equation.calculate());
    }

    @Test
    void testToString() {
        Equation equation = new TestEquation();
        equation.setOperator(Operator.ADD);
        assertEquals("0 + 0 = ", equation.toString());
    }

    @Test
    void getOperand1() {
        Equation equation = new TestEquation();
        assertEquals(0, equation.getOperand1());
    }

    @Test
    void getOperand2() {
        Equation equation = new TestEquation();
        assertEquals(0, equation.getOperand2());
    }

    @Test
    void getOperator() {
        Equation equation = new TestEquation();
        equation.setOperator(Operator.ADD);
        assertEquals(Operator.ADD, equation.getOperator());
    }

    @Test
    void setOperand1() {
        Equation equation = new TestEquation();
        equation.setOperand1(1);
        assertEquals(1, equation.getOperand1());
    }

    @Test
    void setOperand2() {
        Equation equation = new TestEquation();
        equation.setOperand2(1);
        assertEquals(1, equation.getOperand2());
    }

    @Test
    void setOperator() {
        Equation equation = new TestEquation();
        equation.setOperator(Operator.ADD);
        assertEquals(Operator.ADD, equation.getOperator());
    }

    @Test
    void testEquals() {
        Equation equation1 = new TestEquation();
        equation1.setOperand1(1);
        equation1.setOperand2(2);
        equation1.setOperator(Operator.ADD);

        Equation equation2 = new TestEquation();
        equation2.setOperand1(1);
        equation2.setOperand2(2);
        equation2.setOperator(Operator.ADD);

        assertEquals(equation1, equation2);
        assertNotSame(equation1, equation2);

        Equation equation3 = new TestEquation();
        equation3.setOperand1(2);
        equation3.setOperand2(1);
        equation3.setOperator(Operator.ADD);

        assertNotEquals(equation1, equation3);
    }

    @Test
    void canEqual() {
        Equation equation1 = new TestEquation();
        Equation equation2 = new TestEquation();
        assertTrue(equation1.canEqual(equation2));
    }

    @Test
    void testHashCode() {
        Equation equation1 = new TestEquation();
        equation1.setOperand1(1);
        equation1.setOperand2(2);
        equation1.setOperator(Operator.ADD);

        Equation equation2 = new TestEquation();
        equation2.setOperand1(1);
        equation2.setOperand2(2);
        equation2.setOperator(Operator.ADD);

        assertEquals(equation1.hashCode(), equation2.hashCode());
    }
}
