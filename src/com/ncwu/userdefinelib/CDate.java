package com.ncwu.userdefinelib;
/*
 日期类CDate用来描述学生的出生日期，有3个成员变量year、month、day，描述年、月、日。
 类中有无参构造方法，和有参构造方法，以及对成员变量的get和set方法，get方法用来控制成员变量的可读性，set方法用来控制成员变量的可写性。
 */
public class CDate {
    private int year; // 年
    private int month; // 月
    private int day; // 日

    public CDate() {
    }

    public CDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
