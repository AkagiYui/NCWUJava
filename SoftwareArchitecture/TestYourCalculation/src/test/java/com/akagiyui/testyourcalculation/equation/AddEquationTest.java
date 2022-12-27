package com.akagiyui.testyourcalculation.equation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddEquationTest {

    @Test
    @DisplayName("测试toString方法")
    void testToString() {
        Equation equation = new AddEquation((short)1, (short)2);
        assertEquals("1 + 2 = ", equation.toString());
    }

    @Test
    @DisplayName("测试equals方法")
    void testEquals() {
        Equation equation1 = new AddEquation((short)1, (short)2);

        Equation equation2 = new AddEquation((short)1, (short)2);
        assertEquals(equation1, equation2);
        assertNotSame(equation1, equation2);

        Equation equation3 = new AddEquation((short)2, (short)1);
        assertNotEquals(equation1, equation3);
    }

    @Test
    @DisplayName("测试calculate方法")
    void testCalculate() {
        Equation equation = new AddEquation((short)1, (short)2);
        assertEquals(3, equation.calculate());
    }
}
