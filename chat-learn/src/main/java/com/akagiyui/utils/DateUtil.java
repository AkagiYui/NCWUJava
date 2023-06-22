package com.akagiyui.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author AkagiYui
 */
public class DateUtil {
    // 把字符串日期格式化返回Date
    public static Date getDate(String date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            return sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }

    // 把Date日期格式化String形式
    public static String getDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    // 获取计算年龄
    public static int getCalendarAge(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        // 获取年
        int birchYear = c.get(Calendar.YEAR);
        // 获取月份
        int birchMonth = c.get(Calendar.MONTH);

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
