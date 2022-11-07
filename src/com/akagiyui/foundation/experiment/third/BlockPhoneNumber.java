package com.akagiyui.foundation.experiment.third;
/*
模拟通讯大数据行程码屏蔽手机号的若干位，如“151****7580”。
 */
public class BlockPhoneNumber {
    public static void main(String[] args) {
        var phoneNumber = "15112347580";
        // 使用String的replace方法
        var blockPhoneNumber = phoneNumber.replace(phoneNumber.substring(3, 7), "****");
        System.out.println(blockPhoneNumber);
    }
}
