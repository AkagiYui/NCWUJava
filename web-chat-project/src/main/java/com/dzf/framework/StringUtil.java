package com.dzf.framework;

import java.util.ArrayList;
import java.util.List;

/**
 * 字符串工具类
 *
 * @author AkagiYui
 */
public class StringUtil {

    /**
     * 将pattern中的{}替换为objects中的各个object的toString结果
     *
     * @param pattern 模板，形如：{}xx{}xx{}
     * @param objects 要替换的对象
     * @return 替换后的字符串
     */
    public static String format(String pattern, Object... objects) {
        if (pattern == null) {
            return null;
        }
        if (objects == null || objects.length == 0) {
            return pattern;
        }
        StringBuilder sb = new StringBuilder();

        String modelString = "{{#}}";
        pattern = pattern.replace("{}", modelString);

        int leftCursor = 0; // 将要处理的起始位置
        int rightCursor = pattern.indexOf(modelString);  // 下一个{{#}}的起始位置
        int objectIndex = 0; // 当前处理的object的index

        while (rightCursor >= 0) {
            sb.append(pattern, leftCursor, rightCursor);
            sb.append(objects[objectIndex]);
            leftCursor = rightCursor + modelString.length();
            rightCursor = pattern.indexOf(modelString, leftCursor);
            objectIndex++;
        }
        sb.append(pattern, leftCursor, pattern.length());
        return sb.toString();
    }

    /**
     * 将字符串按照separatorChars分割字符串
     *
     * @param str            要分割的字符串
     * @param separatorChars 分割符
     * @return 分割后的字符串数组
     */
    public static String[] split(String str, String separatorChars) {
        if (str == null) {
            return null;
        }
        if (separatorChars == null) {
            return new String[]{str};
        }
        List<String> list = new ArrayList<>();
        int len = str.length();
        int start = 0;
        int end = 0;
        while (end < len) {
            int index = str.indexOf(separatorChars, start);
            if (index == end) {
                list.add("");
                end += separatorChars.length();
                start = end;
            } else if (index == -1) {
                list.add(str.substring(start));
                end = len;
            } else {
                list.add(str.substring(start, index));
                end = index;
                start = end + separatorChars.length();
            }
        }
        //noinspection ToArrayCallWithZeroLengthArrayArgument
        return list.toArray(new String[list.size()]);
    }

    /**
     * 判断字符串是否为空
     *
     * @param str 字符串
     * @return 是否为空
     */
    public static boolean isNotEmpty(String str) {
        return str != null && !"".equals(str);
    }
}
