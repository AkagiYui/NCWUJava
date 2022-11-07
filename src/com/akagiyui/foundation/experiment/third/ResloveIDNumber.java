package com.akagiyui.foundation.experiment.third;

import java.util.HashMap;

/*
通过身份证号码，判断用户的个人信息，包括出生年月日、性别。并输出到控制台。（可以尝试判断出生的省份）
 */
public class ResloveIDNumber {
    public static void main(String[] args) {
        // 使用Map存储省份信息
        var hashMap = new HashMap<String, String>();
        hashMap.put("11", "北京");
        hashMap.put("12", "天津");
        hashMap.put("13", "河北");
        hashMap.put("14", "山西");
        hashMap.put("15", "内蒙古");
        hashMap.put("21", "辽宁");
        hashMap.put("22", "吉林");
        hashMap.put("23", "黑龙江");
        hashMap.put("31", "上海");
        hashMap.put("32", "江苏");
        hashMap.put("33", "浙江");
        hashMap.put("34", "安徽");
        hashMap.put("35", "福建");
        hashMap.put("36", "江西");
        hashMap.put("37", "山东");
        hashMap.put("41", "河南");
        hashMap.put("42", "湖北");
        hashMap.put("43", "湖南");
        hashMap.put("44", "广东");
        hashMap.put("45", "广西");
        hashMap.put("46", "海南");
        hashMap.put("50", "重庆");
        hashMap.put("51", "四川");
        hashMap.put("52", "贵州");
        hashMap.put("53", "云南");
        hashMap.put("54", "西藏");
        hashMap.put("61", "陕西");
        hashMap.put("62", "甘肃");
        hashMap.put("63", "青海");
        hashMap.put("64", "宁夏");
        hashMap.put("65", "新疆");
        hashMap.put("71", "台湾");
        hashMap.put("81", "香港");
        hashMap.put("82", "澳门");
        hashMap.put("91", "国外");

        var idNumber = "420621199910101234";
        var year = idNumber.substring(6, 10);  // 截取年份
        var month = idNumber.substring(10, 12);  // 截取月份
        var day = idNumber.substring(12, 14);  // 截取日期
        var gender = Integer.parseInt(idNumber.substring(16, 17)) % 2 == 0 ? "女" : "男";  // 截取性别
        var province = hashMap.get(idNumber.substring(0, 2));  // 截取省份
        System.out.println("出生年月日：" + year + "年" + month + "月" + day + "日");
        System.out.println("性别：" + gender);
        System.out.println("出生省份：" + province);
    }
}
