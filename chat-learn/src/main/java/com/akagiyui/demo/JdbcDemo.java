package com.akagiyui.demo;

import lombok.Data;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class JdbcDemo {
    // 定义数据库连接参数
    public static String pro_driverClassName;
    public static String pro_url;
    public static String pro_username;
    public static String pro_password;

    public static String dev_driverClassName;
    public static String dev_url;
    public static String dev_username;
    public static String dev_password;

    static {
        // 1、配置数据库
        // ~ 正式库 发布版
        JdbcDemo.pro_driverClassName = "com.mysql.cj.jdbc.Driver";
        JdbcDemo.pro_url = "jdbc:mysql://127.0.0.1:3306/hs";
        JdbcDemo.pro_username = "root";
        JdbcDemo.pro_password = "root";

        // ~ 本地库 开发版
        JdbcDemo.dev_driverClassName = "com.mysql.cj.jdbc.Driver";
        JdbcDemo.dev_url = "jdbc:mysql://127.0.0.1/hs1";
        JdbcDemo.dev_username = "root";
        JdbcDemo.dev_password = "root";
    }


    //封装提示信息
    public static StringBuffer[] sb = {new StringBuffer(), new StringBuffer(), new StringBuffer(), new StringBuffer(), new StringBuffer(), new StringBuffer()};


    //封装表属性
    @Data
    static class Table {
        //表名称
        public String tableName;
        //字段名称+字段类型
        public HashMap<String, String> column;
        //字段名称+字段注解
        public HashMap<String, String> columnComment;
    }

    // 在线版
    public static Connection getTransaction_product() throws Exception {
        Class.forName(pro_driverClassName);
        Connection conn = DriverManager.getConnection(pro_url, pro_username, pro_password);
        if (conn != null) System.out.println("发布版---数据库加载成功！");
        return conn;
    }

    //开发版
    public static Connection getTransaction_develop() throws Exception {
        Class.forName(dev_driverClassName);
        Connection conn = DriverManager.getConnection(dev_url, dev_username, dev_password);
        if (conn != null) System.out.println("开发版---数据库加载成功！");
        return conn;
    }

    // 初始化 比对文件提示信息
    public static void initSbString() {
        sb[0].append("1、本地库存在，正式库不存在的表：\r\n");
        sb[1].append("2、本地库不存在，正式库存在的表：\r\n");
        sb[2].append("3、本地库存在，正式库不存在的字段：\r\n");
        sb[3].append("4、本地库不存在，正式库存在的字段：\r\n");
        sb[4].append("5、表和字段都相同，但字段类型不同的内容：\r\n");
        sb[5].append("6、表和字段、字段类型都相同，但字段注解不同的内容：\r\n");
    }

    /**
     * 比较本地库和正式库的数据表，包括表名、字段名、字段类型、字段注解
     *
     * @param devTable 本地库名称
     * @param proTable 正式库 名称
     * @throws Exception
     */
    public static void compareTables(String devTable, String proTable) throws Exception {

        // 初始化SubString[]
        initSbString();

        // 正式数据库连接
        Connection connectionProduct = getTransaction_product();
        Map<String, Table> mapProduct = getTables(connectionProduct, devTable);

        // 本地数据库连接
        Connection connectionDevelop = getTransaction_develop();
        Map<String, Table> mapDevelop = getTables(connectionDevelop, proTable);

        System.out.println("\n------------开始比对正式库------------");
        // 遍历开发库Map
        for (String keyTable : mapDevelop.keySet()) {
            Table tableDevelop = mapDevelop.get(keyTable);// 获得正式库中的表
            Table tableProduct = mapProduct.get(keyTable);// 尝试从本地库中获得同名表
            if (tableProduct == null) { // 如果获得表为空，说明正式存在，生产不存在
                append(tableDevelop, null, 2);
            } else { // 表相同，判断字段、字段类型、字段注解
                for (String keyColumn : tableDevelop.column.keySet()) {
                    String valueDevelop = tableDevelop.column.get(keyColumn);// 获得正式库中的列
                    String valueColumn = tableProduct.column.get(keyColumn);// 尝试从本地库中获得同名列
                    if (valueColumn == null) {// 如果列名为空，说明正式存在，本地不存在
                        append(tableDevelop, keyColumn, 4);
                    } else {// 说明两者都存在
                        if (!valueColumn.equals(valueDevelop))// 字段类型不一致
                            append(tableDevelop, keyColumn, 5);

                        String commentProduct = tableProduct.columnComment.get(keyColumn);// 获得本地库中的字段注解
                        String developProduct = tableDevelop.columnComment.get(keyColumn);// 尝试从正式库中获得同名字段注解
                        if (!commentProduct.equals(developProduct)) { // 字段注解不一致
                            append(tableDevelop, keyColumn, 6);
                        }
                    }
                }
            }
        }
        System.out.println("------------结束比对正式库------------\n");

        System.out.println("\n***************开始比对本地库***************");
        // 遍历生产库Map
        for (Iterator<String> iter_table = mapProduct.keySet().iterator(); iter_table.hasNext(); ) {
            String key_table = (String) iter_table.next();
            Table table_product = mapProduct.get(key_table);// 从本地库中获得同名表
            Table table_develop = mapDevelop.get(key_table);// 尝试获得正式库中的表
            if (table_develop == null) { // 如果获得表为空，说明本地存在，正式不存在
                append(table_product, null, 1);
            } else { // 表相同，判断字段、字段类型、字段注解
                for (Iterator<String> column_product = table_product.getColumn().keySet().iterator(); column_product.hasNext(); ) {
                    String key_column = (String) column_product.next();
                    String value_column = table_product.column.get(key_column);// 获得本地库中的列
                    String value_develop = table_develop.column.get(key_column);// 尝试从正式库中获得同名列
                    if (value_develop == null) {// 如果列名为空，说明本地存在，正式不存在
                        append(table_develop, key_column, 3);
                    }

                    // 字段相等 类型或者注解不相等上一步已经比对过，这里无需重复比对。
                }
            }
        }
        System.out.println("\n***************结束比对本地库***************");
    }

    //封装TABLE数据
    public static Map<String, Table> getTables(Connection connection, String tableSchema) throws Exception {

        String sSql = "SELECT \n" + "  table_name AS tableName,\n" + "  GROUP_CONCAT(column_name) AS columnName,\n" + "  GROUP_CONCAT(column_type) AS columnType,\n" + "  GROUP_CONCAT(IF(column_comment=\"\",\"无\",column_comment)) AS columnComment\n" + "FROM\n" + "  information_schema.`COLUMNS` \n" + "WHERE table_schema = '" + tableSchema + "' \n" + "GROUP BY tableName\n" + "ORDER BY tableName;";

        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sSql);

        Map<String, Table> map = new HashMap<String, Table>();
        while (rs.next()) {
            Table table = new Table();
            HashMap<String, String> columnMap = new HashMap<>();
            HashMap<String, String> columnCommentMap = new HashMap<>();

            table.setTableName(rs.getString("tableName"));
            String columnName = rs.getString("columnName");
            String columnType = rs.getString("columnType");
            String columnComment = rs.getString("columnComment");
            String columnName_[] = columnName.split(",");
            String columnType_[] = columnType.split(",");
            String columnComment_[] = columnComment.split(",");

            for (int i = 0; i < columnName_.length; i++) {
                columnMap.put(columnName_[i], columnType_[i]);
                columnCommentMap.put(columnName_[i], columnComment_[i]);
            }
            table.setColumn(columnMap);
            table.setColumnComment(columnCommentMap);
            map.put(rs.getString("tableName"), table);
        }
        if (rs != null) rs.close();
        connection.close();
        return map;
    }

    //封装提示信息 （追加到满足条件的StringBuffer）
    public static void append(Table table, String column, int flag) throws Exception {
        switch (flag) {
            case 1:
                System.out.println("1、本地存在，正式不存在的表：" + table.getTableName() + "\r\n");
                sb[0].append(table.getTableName() + "\r\n");
                break;
            case 2:
                System.out.println("2、本地不存在，正式存在的表：" + table.getTableName() + "\r\n");
                sb[1].append(table.getTableName() + "\r\n");
                break;
            case 3:
                System.out.println("3、本地存在，正式不存在的字段：" + table.getTableName() + "[" + column + "]\r\n");
                sb[2].append(table.getTableName() + "[" + column + "]\r\n");
                break;
            case 4:
                System.out.println("4、本地不存在，正式存在的字段：" + table.getTableName() + "[" + column + "]\r\n");
                sb[3].append(table.getTableName() + "[" + column + "]\r\n");
                break;
            case 5:
                System.out.println("5、表和字段都相同，但字段类型不同的内容：" + "[" + column + "][" + table.column.get(column) + "]\r\n");
                sb[4].append(table.getTableName() + "[" + column + "][" + table.column.get(column) + "]\r\n");
                break;
            case 6:
                System.out.println("6、表和字段、字段类型都相同，但字段注解不同的内容：" + table.getTableName() + "[" + column + "][" + table.columnComment.get(column) + "]\r\n");
                sb[5].append(table.getTableName() + "[" + column + "][" + table.columnComment.get(column) + "]\r\n");
                break;
        }
    }

    //写出比对结果到文件  将StringBuffer中的值写入文件中
    public static void writeFile() throws Exception {
        // 合并输出到txt
        StringBuffer rs = new StringBuffer();
        rs.append(sb[0] + "\r\n\r\n").append(sb[1] + "\r\n\r\n").append(sb[2] + "\r\n\r\n").append(sb[3] + "\r\n\r\n").append(sb[4] + "\r\n\r\n").append(sb[5] + "\r\n\r\n");

        File file = new File("C:\\Users\\jbb\\Desktop\\数据库比对结果.txt");
        OutputStream os = new FileOutputStream(file);
        os.write(rs.toString().getBytes());
        os.flush();
        os.close();
    }

}
