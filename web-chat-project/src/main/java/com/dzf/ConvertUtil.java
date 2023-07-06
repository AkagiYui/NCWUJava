package com.dzf;

/**
 * 类型转换工具类
 * @author AkagiYui
 */
public class ConvertUtil {
    /**
     * 将字符串转换为指定类型
     * @param str 字符串
     * @param clazz 指定类型
     * @return 指定类型的对象
     */
    public static Object convert(String str, Class<?> clazz) {
        if (clazz == Integer.class || clazz == int.class) {
            return convert(str);
        } else if (clazz == String.class) {
            return str;
        }
        return null;
    }

    /**
     * 将字符串转换为Integer
     * @param str 字符串
     * @return Integer
     */
    public static Integer convert(String str) {
        return Integer.valueOf(str);
    }
}
