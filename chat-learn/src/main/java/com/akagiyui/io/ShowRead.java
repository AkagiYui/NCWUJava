package com.akagiyui.io;


import com.akagiyui.utils.DateUtil;
import com.akagiyui.utils.ProvinceMapUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class ShowRead {
    public static void main(String[] args) throws Exception {
        //读取姓
        String[] xings = readXing().split(",");
        //读取名
        String[] names = readName().split("");
        //声明年份
        int years[] = {1999, 2000, 2001, 2002};
        //身份证号前两位 省份
        List<String> list = new ArrayList<>();
        for (String key : ProvinceMapUtil.getProvinceMap().keySet()) {
            list.add(key);
        }
        for (int i = 0; i < 10000; i++) {
            //随机数
            Random r = new Random();
            //随机名
            String name = getStuName(r, xings, names);
            //随机身份证号
            String card = getCard(r, r.nextInt(3), list.toArray(), years);
            System.out.println("\n姓名:" + name + "\t***身份证号:" + card);
        }
    }

    //读取姓
    public static String readXing() throws Exception {
        //读取资源文件夹下的文件
        InputStream is = new FileInputStream("C:\\Users\\jbb\\Desktop\\姓.txt");
        //通过转换流 做 字符流
        InputStreamReader isr = new InputStreamReader(is);
        //做 缓存字符流
        BufferedReader br = new BufferedReader(isr);
        //声明
        String xing = br.readLine();
        return xing;
    }

    //读取名
    public static String readName() throws Exception {
        //字符流 读取
        FileReader fr = new FileReader("C:\\Users\\jbb\\Desktop\\名.txt");
        // 转 缓冲流
        BufferedReader br = new BufferedReader(fr);
        //读一行
        String name = br.readLine();
        return name;
    }

    //姓名的方法
    private static String getStuName(Random r, String[] lasts, String[] names) {
        //随机姓数组的索引
        int lastIndex = r.nextInt(lasts.length);
        String last = lasts[lastIndex];
        //随机一个 名1
        int nameIndex = r.nextInt(names.length);
        String name1 = names[nameIndex];

        nameIndex = r.nextInt(names.length);
        String name2 = names[nameIndex];
        //学生的名
        String stuName;
        if (r.nextInt(names.length * 3) > names.length) {
            stuName = last.concat(name1).concat(name2);
        } else {
            stuName = last.concat(name1);
        }
        return stuName;
    }

    //身份证号的方法
    private static String getCard(Random r, int gradeIndex, Object[] ps, int years[]) {
        //获取省份的索引
        int psIndex = r.nextInt(ps.length);
        //随机前四位  通过字符串格式化补0
        String beforeCode = String.format("%04d", r.nextInt(10000));
        //声明一个年份变量
        int year;
        //通过年级随机索引判断
        switch (gradeIndex) {
            case 0:
                year = years[0];
                break;
            case 1:
                year = years[1];
                break;
            case 2:
                year = years[2];
                break;
            default:
                year = years[3];
        }
        //随机月份
        int month = r.nextInt(12) + 1;
        //随机天
        int day;
        if (month == 2) {
            if (year / 4 == 0) {
                day = r.nextInt(29) + 1;
            } else {
                day = r.nextInt(28) + 1;
            }
        } else if ("469".contains(String.valueOf(month)) || "11".equals(month)) {
            day = r.nextInt(30) + 1;
        } else {
            day = r.nextInt(31) + 1;
        }
        //出生年月
        String birth = year + String.format("%02d", month) +
                String.format("%02d", day);
        //出生年月
        Date birthDate = DateUtil.getDate(birth);
        //计算岁数
        int age = DateUtil.getCalendarAge(birthDate);
        //后四位
        int afterIndex = r.nextInt(100000);
        //声明后四位
        String afterCode;
        if (afterIndex > 10000) {
            afterCode = String.valueOf(afterIndex).substring(0, 3) + "X";
        } else {
            afterCode = String.format("%04d", afterIndex);
        }
        //获取性别
        boolean is = afterCode.matches(".{2}[02468][0-9X]");
        //获取身份证号
        String card = ps[psIndex] + beforeCode + birth + afterCode;
        return card;
    }

}
