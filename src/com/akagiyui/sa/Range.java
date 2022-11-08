package com.akagiyui.sa;

/**
 * 范围类
 */
public class Range {
    public final short start; // 最小值
    public final short end; // 最大值

    public Range(short start, short end) {
        if (start > end) {
            // 最小值不能大于最大值
            throw new IllegalArgumentException("start > end");
        }
        this.start = start;
        this.end = end;
    }

    /**
     * 判断值是否在范围内
     * @param value 值
     * @return 是否在范围内
     */
    public boolean contains(short value) {
        return value >= start && value <= end;
    }
}
