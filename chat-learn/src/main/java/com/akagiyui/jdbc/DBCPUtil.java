package com.akagiyui.jdbc;

import org.apache.commons.dbcp2.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class DBCPUtil {
    //数据源对象
    private static DataSource dataSource;
    //连接对象
    private static Connection con;
    //预编译sql对象
    private static PreparedStatement pre;
    //结果集
    private static ResultSet rs;
    static {
        try {
            //getResourceAsStream读取resource文件夹下配置文件
            InputStream is = DBCPUtil.class.getClassLoader().getResourceAsStream("dbcp.properties");
            //实例化对象
            Properties properties = new Properties();
            //通过Properties对象加载文件
            properties.load(is);
            //jdbc连接池 是通过DataSource数据源获取连接
            //通过连接池工厂获取DataSource对象
            dataSource = BasicDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
