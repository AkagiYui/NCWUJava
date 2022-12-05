package com.akagiyui.testyourcalculation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RangeTest {
    @Test
    void testRange() {
        Range range = new Range((short) 1, (short) 10);
        assertEquals(1, range.getStart());
        assertEquals(10, range.getEnd());
    }

    @Test
    void contains() {
        Range range = new Range((short)1, (short)10);
        assertTrue(range.contains((short)1));
        assertTrue(range.contains((short)5));
        assertTrue(range.contains((short)10));
        assertFalse(range.contains((short)0));
        assertFalse(range.contains((short)11));
    }

    @Test
    void getStart() {
        Range range = new Range((short)1, (short)10);
        assertEquals(1, range.getStart());
    }

    @Test
    void getEnd() {
        Range range = new Range((short)1, (short)10);
        assertEquals(10, range.getEnd());
    }
}