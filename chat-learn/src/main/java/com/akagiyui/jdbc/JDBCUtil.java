package com.akagiyui.jdbc;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * JDBC:步骤
 * 1、导入驱动包
 * 2、加载驱动(注册)
 * 3、获取连接
 * 4、获取执行sql对象
 * 5、通过对象执行sql
 *
 * @author AkagiYui
 */
public class JDBCUtil {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/hs";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static Connection con = null;
    private static PreparedStatement pre = null;
    private static ResultSet res = null;

    static {//静态代码块 只会初始化一次
        // 加载驱动(注册)
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //返回 PreparedStatement
    private static PreparedStatement getPreparedStatement(String sql, Object... obj) {
        try {
            //创建执行sql对象，并且加载预编译sql
            pre = con.prepareStatement(sql);
            //给预编译sql占位符 赋值
            for (int i = 0; i < obj.length; i++) {
                //parameterIndex 占位符的索引为从 1 开始
                pre.setObject((i + 1), obj[i]);
            }
            //输入执行sql
            System.out.println("sql语句执行:" + pre.toString().split(":")[1]);
            return pre;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 执行增删改 sql 返回boolean类型
    public static boolean jdbcExecute(String sql, Object... args) {
        try {
            //false:成功  true:失败
            return !pre.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
//            close();
        }
        return false;
    }

    // 执行增删改 sql 返回int类型
    public static int jdbcExecuteUpdate(String sql, Object... args) {
        try {
            //false:成功  true:失败
            return pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
//            close();
        }
        return -1;
    }

    // 返回一个Map对象
    public static Map<String, Object> oneMapExecute(String sql, Object... args) {
        //声明一个Map对象
        Map<String, Object> map = new HashMap<>();
        try {
            pre = getPreparedStatement(sql, args);
            //执行sql
            res = pre.executeQuery();
            //获取元数据
            ResultSetMetaData rsd = res.getMetaData();
            //判断结果集是否为null 进行遍历获取
            while (res.next()) {
                //判断 字段个数
                for (int i = 1; i <= rsd.getColumnCount(); i++) {
                    //获取字段名
                    String key = rsd.getColumnLabel(i);
                    //获取值
                    Object value = res.getObject(i);
                    map.put(key, value);
                }
            }
            return map;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
//            close();
        }
        return null;
    }

    //返回多条数据List<Map<String,Object>>
    public static List<Map<String, Object>> manyMapExecute(String sql, Object... obj) {
        //声明一个List集合容器
        List<Map<String, Object>> list = new ArrayList<>();
        try {
            pre = getPreparedStatement(sql, obj);

            res = pre.executeQuery();
            //通过ResultSet获取元数据
            ResultSetMetaData md = res.getMetaData();
            while (res.next()) {
                Map<String, Object> map = new HashMap<>();
                //获取查询字段的单元数据
                for (int i = 1; i <= md.getColumnCount(); i++) {
                    //获取查询字段名
                    String key = md.getColumnLabel(i);
                    //根据字段名获取查询单元数据
                    Object value = res.getObject(key);
                    //把key与value存入map
                    map.put(key, value);
                }
                //把map存放到List容器里
                list.add(map);
            }

            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
//            close();
        }
        return null;
    }

    // 返回一个实体类
    public static <T> T oneEntryExecute(String sql, Class<T> cla, Object... args) {

        try {
            //反射实例化对象
            T t = cla.getConstructor().newInstance();

            pre = getPreparedStatement(sql, args);
            //执行sql
            res = pre.executeQuery();
            //获取元数据
            ResultSetMetaData rsd = res.getMetaData();
            //判断结果集是否为null 进行遍历获取
            while (res.next()) {
                //判断 字段个数
                for (int i = 1; i <= rsd.getColumnCount(); i++) {
                    //获取字段名
                    String key = rsd.getColumnLabel(i);
                    //获取值
                    Object value = res.getObject(i);
                    try {
                        //属性扫描器
                        PropertyDescriptor pd = new PropertyDescriptor(key, cla);
                        //获取写入的方法 获取set方法
                        Method method = pd.getWriteMethod();
                        //执行set方法 进行赋值
                        method.invoke(t, value);
                    } catch (Exception e) {
                        System.err.println(key + "\t该字段名与属性名不一致!");
                    }

                }
            }
            return t;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            close();
        }
        return null;
    }

    //查询 返回List<T>  满足查询出多条数据
    public static <T> List<T> manyEntryExecute(String sql, Class<T> cla, Object... obj) {
        List<T> list = new ArrayList<>();
        try {
            pre = getPreparedStatement(sql, obj);

            res = pre.executeQuery();
            //通过ResultSet获取元数据
            ResultSetMetaData rsd = res.getMetaData();
            while (res.next()) {
                //实例化 泛型对象
                T t = cla.getConstructor().newInstance();
                //通过元数据获取查询的字段个数 和 字段名
                for (int i = 1; i <= rsd.getColumnCount(); i++) {
                    //通过索引获取查询字段名
                    String key = rsd.getColumnLabel(i);
                    //根据字段名获取单元数据
                    Object value = res.getObject(key);
                    try {
                        PropertyDescriptor pd = new PropertyDescriptor(key, cla);
                        //通过PropertyDescriptor找到属性的set方法 然后赋值
                        Method method = pd.getWriteMethod();
                        //通过Method的invoke()做对象的方法参数传参  实际做了set赋值
                        method.invoke(t, value);
                    } catch (Exception e) {
                        System.err.println(key + "\t该字段名与属性名不一致!");
                    }

                }
                //把泛型对象存入到List容器里
                list.add(t);
            }
            //数据存入完 返回list集合
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            close();
        }
        return null;
    }

    //关闭对象
    private static void close() {
        try {
            if (res != null) {
                res.close();
            }
            if (pre != null) {
                pre.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
