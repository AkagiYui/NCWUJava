package main.java.com.akagiyui.sa;

import lombok.Getter;

/**
 * 范围类
 */
@SuppressWarnings("ClassCanBeRecord")
@Getter // 生成 getter 方法
public class Range {
    private final short start; // 最小值
    private final short end; // 最大值

    /**
     * 构造方法
     * @param start 最小值
     * @param end 最大值
     */
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
