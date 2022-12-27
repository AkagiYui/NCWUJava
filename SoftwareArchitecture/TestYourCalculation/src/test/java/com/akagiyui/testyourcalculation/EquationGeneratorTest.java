package com.akagiyui.testyourcalculation;

import com.akagiyui.testyourcalculation.checker.EquationRangeChecker;
import com.akagiyui.testyourcalculation.equation.Equation;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EquationGeneratorTest {

    @Test
    void getInstance() {
        var generator1 = EquationGenerator.getInstance();
        var generator2 = EquationGenerator.getInstance();
        assertEquals(generator1, generator2);
    }

    @Test
    void generate() {
        var generator = EquationGenerator.getInstance();
        var checker = new EquationRangeChecker(new Range(0, 100));
        generator.generate(10, checker);
        List<Equation> equations = new ArrayList<>();
        generator.forEach(equations::add);
        assertEquals(10, equations.size());
    }

    @Test
    void hasNext() {
        var generator = EquationGenerator.getInstance();
        var checker = new EquationRangeChecker(new Range(0, 100));
        generator.generate(10, checker);
        assertTrue(generator.hasNext());
    }

    @Test
    void next() {
        var generator = EquationGenerator.getInstance();
        var checker = new EquationRangeChecker(new Range(0, 100));
        generator.generate(10, checker);
        var equation = generator.next();
        assertNotNull(equation);
    }

    @Test
    void iterator() {
        var generator = EquationGenerator.getInstance();
        var checker = new EquationRangeChecker(new Range(0, 100));
        generator.generate(10, checker);
        var iterator = generator.iterator();
        assertNotNull(iterator);
    }
}
