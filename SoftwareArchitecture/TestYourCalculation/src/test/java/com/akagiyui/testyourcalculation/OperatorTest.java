package com.akagiyui.testyourcalculation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OperatorTest {

    @Test
    void getOperator() {
        Operator operator = Operator.ADD;
        assertEquals('+', operator.getOperator());

        operator = Operator.SUB;
        assertEquals('-', operator.getOperator());
    }

    @Test
    void values() {
        Operator[] operators = Operator.values();
        assertEquals(2, operators.length);
        assertEquals(Operator.ADD, operators[0]);
        assertEquals(Operator.SUB, operators[1]);
    }

    @Test
    void valueOf() {
        Operator operator = Operator.valueOf("ADD");
        assertEquals(Operator.ADD, operator);
    }
}
