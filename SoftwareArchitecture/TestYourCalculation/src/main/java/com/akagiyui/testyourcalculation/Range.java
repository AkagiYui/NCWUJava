package com.akagiyui.testyourcalculation;

import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * 范围类
 * 只读
 */
@Getter // 生成 getter 方法
@EqualsAndHashCode // 生成 equals 和 hashCode 方法
public class Range {
    /**
     * 最小值
     */
    private final int min;
    /**
     * 最大值
     */
    private final int max;

    /**
     * 范围
     * @param min 最小值
     * @param max 最大值
     */
    public Range(int min, int max) {
        if (min > max) {
            // 最小值不能大于最大值
            throw new IllegalArgumentException("min > max");
        }
        this.min = min;
        this.max = max;
    }

    /**
     * 判断值是否在范围内
     * @param value 值
     * @return 是否在范围内
     */
    public boolean contains(int value) {
        return value >= min && value <= max;
    }
}
