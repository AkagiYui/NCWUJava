package com.dzf.framework.mybatis;

import lombok.extern.slf4j.Slf4j;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据库操作
 *
 * @author AkagiYui
 */
@Slf4j
public class Database {
    static String DRIVER = "com.mysql.cj.jdbc.Driver";
    static String URL = "jdbc:mysql://localhost:3306/mysql";
    static String USERNAME = "root";
    static String PASSWORD = "";
    static Connection connection;

    static {
        // 注册驱动
        try {
            Class.forName(DRIVER);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 连接数据库
     */
    static void connect() {
        log.debug("数据库地址：{}", URL);
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            if (!connection.isClosed()) {
                log.debug("数据库连接成功");
            }
        } catch (SQLException e) {
            log.error("数据库连接失败");
            throw new RuntimeException(e);
        }
    }

    /**
     * 创建预编译sql对象
     *
     * @param sql sql语句
     * @param obj sql参数
     * @return 预编译sql对象
     */
    private static PreparedStatement getPreparedStatement(String sql, Object... obj) {
        try {
            // 创建执行sql对象，并且加载预编译sql
            PreparedStatement pre = connection.prepareStatement(sql);
            // 给预编译sql占位符 赋值
            for (int i = 0; i < obj.length; i++) {
                // parameterIndex 占位符的索引为从 1 开始
                pre.setObject((i + 1), obj[i]);
            }
            log.debug("sql语句编译：{}", pre.toString().split(":")[1]);
            return pre;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 执行 增删改
     *
     * @param sql  sql语句
     * @param args sql参数
     * @return 影响行数
     */
    public static int jdbcExecute(String sql, Object... args) {
        try {
            return getPreparedStatement(sql, args).executeUpdate();
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 执行 查询
     *
     * @param sql  sql语句
     * @param args sql参数
     * @return Map对象 {字段名：字段值}
     */
    public static Map<String, Object> oneMapExecute(String sql, Object... args) {
        Map<String, Object> map = new HashMap<>();
        try {
            PreparedStatement pre = getPreparedStatement(sql, args);
            ResultSet res = pre.executeQuery();
            // 获取元数据
            ResultSetMetaData rsd = res.getMetaData();
            // 判断结果集是否为null 进行遍历获取
            while (res.next()) {
                // 判断 字段个数
                for (int i = 1; i <= rsd.getColumnCount(); i++) {
                    String key = rsd.getColumnLabel(i);
                    Object value = res.getObject(i);
                    map.put(key, value);
                }
            }
            return map;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 执行 查询
     *
     * @param sql sql语句
     * @param obj sql参数
     * @return List<Map>对象 [{字段名：字段值}, ...]
     */
    public static List<Map<String, Object>> manyMapExecute(String sql, Object... obj) {
        List<Map<String, Object>> list = new ArrayList<>();
        try {
            PreparedStatement pre = getPreparedStatement(sql, obj);
            ResultSet res = pre.executeQuery();
            // 通过ResultSet获取元数据
            ResultSetMetaData md = res.getMetaData();
            while (res.next()) {
                Map<String, Object> map = new HashMap<>();
                // 获取查询字段的单元数据
                for (int i = 1; i <= md.getColumnCount(); i++) {
                    String key = md.getColumnLabel(i);
                    Object value = res.getObject(key);
                    map.put(key, value);
                }
                list.add(map);
            }

            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 执行 查询
     *
     * @param sql  sql语句
     * @param cla  实体类
     * @param args sql参数
     * @return 实体类对象
     */
    public static <T> T oneEntryExecute(String sql, Class<T> cla, Object... args) {
        try {
            PreparedStatement pre = getPreparedStatement(sql, args);
            ResultSet res = pre.executeQuery();
            //获取元数据
            ResultSetMetaData rsd = res.getMetaData();
            boolean exist = false;
            //反射实例化对象
            T t = cla.getConstructor().newInstance();
            //判断结果集是否为null 进行遍历获取
            while (res.next()) {
                exist = true;
                //判断 字段个数
                for (int i = 1; i <= rsd.getColumnCount(); i++) {
                    String key = rsd.getColumnLabel(i);
                    Object value = res.getObject(i);
                    try {
                        //属性扫描器
                        PropertyDescriptor pd = new PropertyDescriptor(key, cla);
                        //获取写入的方法 获取set方法
                        Method method = pd.getWriteMethod();
                        //执行set方法 进行赋值
                        method.invoke(t, value);
                    } catch (Exception e) {
                        log.error(key + "\t该字段名与属性名不一致!");
                    }

                }
            }
            return exist ? t : null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 执行 查询
     *
     * @param sql sql语句
     * @param cla 实体类
     * @param obj sql参数
     * @return List<实体类对象>
     */
    public static <T> List<T> manyEntryExecute(String sql, Class<T> cla, Object... obj) {
        List<T> list = new ArrayList<>();
        try {
            PreparedStatement pre = getPreparedStatement(sql, obj);
            ResultSet res = pre.executeQuery();
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
                        log.error(key + "\t该字段名与属性名不一致!");
                    }

                }
                list.add(t);
            }
            //数据存入完 返回list集合
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 执行 查询 一对多
     *
     * @param sql       sql语句
     * @param clazz     一对多的实体类
     * @param oneSql    一对多的sql
     * @param oneId     一对多的id
     * @param oneClazz  一对多的实体类
     * @param oneMethod 一对多的方法
     * @param obj       sql参数
     * @param <T>       泛型
     * @return 实体对象
     */
    public static <T> T getOneToManySelect(String sql, Class<T> clazz, String oneSql, String oneId, Class<T> oneClazz, Method oneMethod, Object... obj) {
        try {
            //泛型实例化
            T t = clazz.getConstructor().newInstance();
            //获取PreparedStatement对象
            PreparedStatement pre = getPreparedStatement(sql, obj);
            //执行sql返回ResultSet结果集
            ResultSet res = pre.executeQuery();
            //通过ResultSet获取元数据
            ResultSetMetaData md = res.getMetaData();
            while (res.next()) {
                //通过for循环获取单元数据
                //getColumnCount()获取查询字段个数
                for (int i = 1; i <= md.getColumnCount(); i++) {
                    //获取查询字段名
                    String key = md.getColumnLabel(i);
                    //Method执行方法赋值
                    Object value = res.getObject(key);
                    try {
                        //属性扫描器  检测该类是否有该属性
                        PropertyDescriptor pd = new PropertyDescriptor(key, clazz);
                        //getWriteMethod() 获取的就是该属性的set方法
                        Method method = pd.getWriteMethod();
                        //执行方法 参数传值
                        method.invoke(t, value);
                    } catch (Exception e) {
                        System.err.println(key + "\t该字段名与属性名不一致!");
                    }
                }
            }
            //获取属性
            Field[] fields = clazz.getDeclaredFields();
            //获取返回类型
            Type mType = oneMethod.getGenericReturnType();
            for (Field f : fields) {
                //获取属性类型
                Type fType = f.getGenericType();
                if (fType.equals(mType)) {
                    f.setAccessible(true);
                    //给属性赋值
                    f.set(t, manyEntryExecute(oneSql, oneClazz, oneId));
                }
            }
            return t;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 关闭数据库连接
     */
    private static void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
