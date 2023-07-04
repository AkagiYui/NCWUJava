package com.dzf.framework;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 *
 * @author AkagiYui
 */
public class DateUtil {
    /**
     * 字符串转换成日期
     *
     * @param date 20190101
     * @return date
     */
    public static Date getDate(String date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            return sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }

    /**
     * 日期转换成字符串
     *
     * @param date date
     * @return yyyy-MM-dd
     */
    public static String getDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    /**
     * 获取当前时间
     *
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String getDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }

    /**
     * 计算年龄
     *
     * @param date 日期对象
     * @return 年龄
     */
    public static int getAge(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        int birchYear = c.get(Calendar.YEAR); // 获取年
        int birchMonth = c.get(Calendar.MONTH); // 获取月份

        c.setTime(new Date());
        int currentYear = c.get(Calendar.YEAR);
        int currentMonth = c.get(Calendar.MONTH);
        if (birchMonth > currentMonth) {
            return currentYear - birchYear;
        } else {
            return (currentYear - birchYear) + 1;
        }
    }
}
