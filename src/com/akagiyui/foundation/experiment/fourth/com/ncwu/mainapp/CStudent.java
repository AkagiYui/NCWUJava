package com.akagiyui.foundation.experiment.fourth.com.ncwu.mainapp;

import com.akagiyui.foundation.experiment.fourth.com.ncwu.userdefinelib.CDate;

/*
学生类CStudent用来描述学生的基本信息和行为。类中有实例变量sno、sname、sdate，
描述学生的学号、姓名和出生日期；类变量count，统计学生对象的个数，
有无参构造和三个参数构造方法，以及私有成员变量set和get方法，get方法用来控制成员变量的可读性。
 */
public class CStudent {
    private String sno; // 学号
    private String sname; // 姓名
    private CDate sdate; // 出生日期
    private static int count;

    public CStudent() {
        count++;
    }

    public CStudent(String sno, String sname, CDate sdate) {
        this.sno = sno;
        this.sname = sname;
        this.sdate = sdate;
        count++;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public CDate getSdate() {
        return sdate;
    }

    public void setSdate(CDate sdate) {
        this.sdate = sdate;
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        CStudent.count = count;
    }
}
