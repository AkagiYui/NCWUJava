package com.akagiyui.testyourcalculation;

import com.akagiyui.testyourcalculation.equation.AddEquation;
import com.akagiyui.testyourcalculation.equation.Equation;
import com.akagiyui.testyourcalculation.equation.SubEquation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class EquationFactoryTest {

    @Test
    void getAddEquation() {
        Equation equation = EquationFactory.getEquation("add");
        assertTrue(equation instanceof AddEquation);
    }

    @Test
    void getSubEquation() {
        Equation equation = EquationFactory.getEquation("sub");
        assertTrue(equation instanceof SubEquation);
    }
}