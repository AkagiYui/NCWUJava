package com.akagiyui.web.kenkoweb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Rectangle {
    private int length;
    private int width;

    public int getArea() {
        return length * width;
    }
}
