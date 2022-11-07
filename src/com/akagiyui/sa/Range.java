package com.akagiyui.sa;

/**
 * 范围类
 * @param start 最小值
 * @param end 最大值
 */
public record Range(short start, short end) {
    public Range {
        if (start > end) {
            // 最小值不能大于最大值
            throw new IllegalArgumentException("start > end");
        }
    }
}
