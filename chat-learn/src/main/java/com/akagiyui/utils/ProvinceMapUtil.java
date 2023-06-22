package com.akagiyui.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author AkagiYui
 */
public class ProvinceMapUtil {
    private static final Map<String, String> MAP;

    public static void main(String[] args) {
        Set<String> keys = MAP.keySet();
        for (String key : keys) {
            System.out.print("\"" + key + "\",");
        }
    }

    static {
        // 初始化集合只被加载一次
        MAP = new HashMap<>();
        MAP.put("11", "北京市");
        MAP.put("12", "天津市");
        MAP.put("13", "河北省");
        MAP.put("14", "山西省");
        MAP.put("15", "内蒙古自治区");
        MAP.put("21", "辽宁省");
        MAP.put("22", "吉林省");
        MAP.put("23", "黑龙江省");
        MAP.put("31", "上海市");
        MAP.put("32", "江苏省");
        MAP.put("33", "浙江省");
        MAP.put("34", "安徽省");
        MAP.put("35", "福建省");
        MAP.put("36", "江西省");
        MAP.put("37", "山东省");
        MAP.put("41", "河南省");
        MAP.put("42", "湖北省");
        MAP.put("43", "湖南省");
        MAP.put("44", "广东省");
        MAP.put("45", "广西壮族自治区");
        MAP.put("46", "海南省");
        MAP.put("50", "重庆市");
        MAP.put("51", "四川省");
        MAP.put("52", "贵州省");
        MAP.put("53", "云南省");
        MAP.put("54", "西藏自治区");
        MAP.put("61", "陕西省");
        MAP.put("62", "甘肃省");
        MAP.put("63", "青海省");
        MAP.put("64", "宁夏回族自治区");
        MAP.put("65", "新疆维吾尔自治区");
        MAP.put("71", "台湾省");
        MAP.put("81", "香港特别行政区");
        MAP.put("82", "澳门特别行政区");

    }

    // 通过MAP的key直接获取value
    // 获取省编号集合
    public static Map<String, String> getProvinceMap() {
        return MAP;
    }

    // 通过编号获取具体省
    public static String getProvince(String number) {
        return MAP.get(number);
    }
}
