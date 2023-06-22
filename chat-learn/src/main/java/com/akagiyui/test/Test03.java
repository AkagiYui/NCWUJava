package com.akagiyui.test;

import com.akagiyui.demo.JdbcDemo;

/**
 * @author AkagiYui
 */
public class Test03 {

    public static void main(String[] args) throws Exception {
        //传入本地库 和正式库 数据库名称
        JdbcDemo.compareTables("hs","hs1");
        // 写入文件
        JdbcDemo.writeFile();
    }
}
