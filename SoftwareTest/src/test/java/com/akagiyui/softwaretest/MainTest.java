package com.akagiyui.softwaretest;

import org.junit.jupiter.api.Test;

import static com.akagiyui.softwaretest.Main.getTriangleType;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void getTriangleTypeTest() {
        assertEquals("非法输入", getTriangleType(0, 0, 0));
        assertEquals("非法输入", getTriangleType(0, 1, 1));
        assertEquals("非法输入", getTriangleType(1, 0, 1));
        assertEquals("非法输入", getTriangleType(1, 1, 0));
        assertEquals("非法输入", getTriangleType(-1, -1, -1));
        assertEquals("非法输入", getTriangleType(-1, 1, 1));
        assertEquals("非法输入", getTriangleType(1, -1, 1));
        assertEquals("非法输入", getTriangleType(1, 1, -1));
        assertEquals("非法输入", getTriangleType(1, 1, 2));
        assertEquals("非法输入", getTriangleType(1, 2, 1));
        assertEquals("非法输入", getTriangleType(2, 1, 1));
        assertEquals("非法输入", getTriangleType(1, 1, 2));
        assertEquals("非法输入", getTriangleType(1, 2, 1));
        assertEquals("非法输入", getTriangleType(2, 1, 1));
        assertEquals("非法输入", getTriangleType(1, 2, 3));

        assertEquals("等边三角形", getTriangleType(1, 1, 1));
        assertEquals("等边三角形", getTriangleType(2, 2, 2));
        assertEquals("等边三角形", getTriangleType(3, 3, 3));

        assertEquals("等腰三角形", getTriangleType(2, 2, 3));
        assertEquals("等腰三角形", getTriangleType(2, 3, 2));
        assertEquals("等腰三角形", getTriangleType(3, 2, 2));

        assertEquals("一般三角形", getTriangleType(2, 3, 4));
        assertEquals("一般三角形", getTriangleType(3, 4, 2));
        assertEquals("一般三角形", getTriangleType(4, 2, 3));
        assertEquals("一般三角形", getTriangleType(3, 4, 5));
    }
}
