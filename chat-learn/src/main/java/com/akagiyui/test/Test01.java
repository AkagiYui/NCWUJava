package com.akagiyui.test;

import java.util.Arrays;

/**
 * @author AkagiYui
 */
public class Test01 {
    /**
     * 1、计算机概念：
     * 1字节 = 8位
     * 二进制值数据处理存储，
     * 二进制:最小值:0000 0000     最大值：1111 1111
     * 十进制: 0  ~  255
     * 计算机语言:
     * 二进制:
     * 最高位位符号位: 0:正数   1:负数
     * 小值:0000 0000  ~  1111 1111
     * 大值:0111 1111  ~  1000 0000
     * 十进制:  0 ~ 127   -0~ -127  (-0指的就是-128)
     * <p>
     * 源码--》 反码  --》补码
     * 例如:   正数:2               负数:-2
     * 源码:  0000 0010        1000 0010
     * 反码:  0000 0010        1111 1101（负数的反码符号位不变，其它取反)
     * 补码:  0000 0010        1111 1110(负数低位加+1)
     */
    public static void main(String[] args) {//主方法:1、一个进程 也是主线程  CG:垃圾回收线程
        //Java的基本类型
        // baseType();
        //Java数组类型
        baseArray();
    }

    // Java数组类型
    public static void baseArray() {
        /**
         * Java数组类型:
         *      1、Java基本类型数组   2、Java引用类型数组
         * 特点:
         *    a、数组元素类型要一致
         *    b、在声明数组时确定长度
         *    c、三种声明方式:
         *       通过元素确定:  例如:int[] a = {1,2,3};
         *       通过实例化:   例如: int[] a = new int[3];
         *       通过实例元素: 例如: int[] a = new int[]{1,2,3}
         */
        int[] aa = {1, 9, 7, 5, 2};
        //Arrays工具类:
        //1、需求 输出数据元素
        System.out.println(Arrays.toString(aa));
        //2、扩展 数组大小
        int[] bb = Arrays.copyOf(aa, 10);
        System.out.println("这个是原数组:" + Arrays.toString(aa));
        System.out.println("这个是扩展后数组:" + Arrays.toString(bb));
        //3、搜索  判断是否元素存在以及下坐标索引
        int index = Arrays.binarySearch(aa, 0);
        System.out.println("索引值:" + index);
        //4、排序  无返回值
        Arrays.sort(aa);
        System.out.println("排序后:" + Arrays.toString(aa));
    }

    //Java的基本类型
    public static void baseType() {
        /**
         * Java基本类型:四大类:
         *       整型: byte(1字节) short(3字节) int(4字节) long(8字节)
         *       浮点型: float(4字节) long(8字节)   科学计算法
         *      布尔型: boolean (1字节)
         *      字符型: char(2字节)
         *
         * 码表:
         *    ASLCC(小码表):0~127 (字母大小写、数字或特殊符号)
         *    unicode(大码表):国际通用:utf-8/utf-16/ytf-32
         *    gbk:国内编码gbk2312
         *
         * 编码: 通过一种码值存储数据
         * 解码: 通过对存储数据码值的解码 还原数据
         */

        // byte、char、short运算时会自动提升int类型
        byte a = 'c';
        short b = 10;
        int c = a + b;
        System.out.println(c);

        // 大类型(指字节数)转小类型:强类型转换
        int aa = -1;
        byte bb = (byte) aa;
        System.out.println(bb);
        //小类型(指字节数)转大类型:自动类型转换
        short cc = 10000;
        aa = cc;
        System.out.println(aa);
    }

}
