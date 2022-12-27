package com.akagiyui.testyourcalculation.checker;

import com.akagiyui.testyourcalculation.Range;
import com.akagiyui.testyourcalculation.equation.AddEquation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EquationRangeCheckerTest {

    @Test
    void check() {
        EquationRangeChecker checker = new EquationRangeChecker(new Range(0, 100));
        assertTrue(checker.check(new AddEquation(1, 2)));
        assertFalse(checker.check(new AddEquation(1, 101)));
    }

    @Test
    void getRange() {
        EquationRangeChecker checker = new EquationRangeChecker(new Range(0, 100));
        assertEquals(new Range(0, 100), checker.getRange());
    }
}
