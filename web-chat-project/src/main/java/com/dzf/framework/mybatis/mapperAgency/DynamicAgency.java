package com.dzf.framework.mybatis.mapperAgency;


import com.dzf.framework.mybatis.Database;
import com.dzf.framework.mybatis.Mybatis;
import com.dzf.framework.mybatis.annotation.sql.One;
import com.dzf.framework.mybatis.pojo.MapperQuery;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 动态代理执行注解sql
 *
 * @author AkagiYui
 */
@Slf4j
public class DynamicAgency<T> implements InvocationHandler {
    /**
     * Mapper方法信息 {包名.类名.方法名, 方法信息}
     */
    private static final Map<String, MapperQuery> MAPPER_QUERY_MAP = new HashMap<>();

    static {
        try {
            log.debug("=============解析Mapper接口=============");
            MAPPER_QUERY_MAP.putAll(Mybatis.parseMapperClass("com.akagiyui.mapper"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 代理对象
     */
    private Object obj;
    /**
     * 代理接口
     */
    private Class<T> inf;

    public DynamicAgency(Object obj) {
        this.obj = obj;
    }

    public DynamicAgency(Class<T> inf) {
        this.inf = inf;
    }

    public Object getInterface() {
        return Proxy.newProxyInstance(inf.getClassLoader(), new Class[]{inf}, this);
    }

    public Object getObject() {
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), this);
    }

    /**
     * 执行代理方法
     *
     * @param proxy  代理对象
     * @param method 代理方法
     * @param args   代理方法参数
     * @return 方法返回值
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.debug("=============代理执行Mapper方法=============");
        String methodName = method.getName();
        log.debug("方法名：{}", methodName);

        if (obj != null) { // 如果是代理对象，直接执行
            return method.invoke(obj, args);
        }

        if ("toString".equals(methodName)) {
            return methodName;
        }

        // 包名.类名.方法名
        String key = method.getDeclaringClass().getName() + "." + method.getName();
        MapperQuery mapperQuery = MAPPER_QUERY_MAP.get(key);
        String sql = mapperQuery.getSql(); // 获取sql

        Class<?> returnType = method.getReturnType(); // 方法返回值

        // sql是空字符串，返回null
        if ("".equals(sql)) {
            log.error("sql为空字符串");
            return switch (returnType.getName()) {
                case "int", "long", "short", "byte" -> 0;
                case "float" -> 0.0f;
                case "double" -> 0.0d;
                case "boolean" -> false;
                case "char" -> '\u0000';
                default -> null;
            };
        }

        // 判断返回值类型，执行对应的方法
        String returnTypeName = returnType.getCanonicalName(); // 返回值类型
        switch (returnTypeName) {
            case "java.util.Map" -> Database.oneMapExecute(sql, args);
            case "java.util.List" -> {
                Type type = method.getGenericReturnType();
                if ("java.util.List<java.util.Map<java.lang.String, java.lang.Object>>".equals(type.getTypeName())) {
                    return Database.manyMapExecute(sql, args);
                } else {
                    if (type instanceof ParameterizedType) {
                        //获取实际类型参数 getActualTypeArguments()
                        Type[] ty = ((ParameterizedType) type).getActualTypeArguments();
                        return Database.manyEntryExecute(sql, Class.forName(ty[0].getTypeName()), args);
                    }
                }
            }
            default -> {
                //判断是否做一对多关系
                One one = mapperQuery.getOne();
                if ("".equals(one.select())) {
                    return Database.oneEntryExecute(sql, Class.forName(returnTypeName), args);
                }
                //获取一对多关系的sql
                Class clazz = Class.forName(returnTypeName);
                Field[] fields = clazz.getDeclaredFields();
                //获取对应的返回类型
                String path = one.path();
                //包名.类名
                String clazzPath = path.substring(0, path.lastIndexOf("."));
                //方法名
                String methodPath = path.substring((path.lastIndexOf(".") + 1), path.length());
                Class<?> oneClazz = Class.forName(clazzPath);
                Method oneMethod = oneClazz.getMethod(methodPath);
                return Database.getOneToManySelect(sql, clazz, one.select(), one.id(), oneClazz, oneMethod, args);
            }
        }

        log.error("未找到对应的返回值类型：{}", returnTypeName);
        return null;
    }
}
