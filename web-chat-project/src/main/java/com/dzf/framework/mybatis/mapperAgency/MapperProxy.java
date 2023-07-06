package com.dzf.framework.mybatis.mapperAgency;

import com.dzf.framework.mybatis.Database;
import com.dzf.framework.mybatis.annotation.sql.Select;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Mapper 代理
 * @deprecated
 *
 * @author AkagiYui
 */
@Slf4j
public class MapperProxy {
    /** Mapper 代理对象 {全路径类名: 代理对象} */
    static final Map<String, Object> MAPPER_BEANS = new HashMap<>();

    /**
     * 获取 Mapper 代理
     *
     * @param clazz Mapper 接口
     * @param <T>   Mapper 接口类型
     * @return Mapper 代理
     */
    public static <T> T getMapper(Class<?> clazz) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, MapperProxy::execute);
    }

    /**
     * 执行 Mapper 方法
     * @param proxy 代理对象
     * @param method 方法
     * @param args 参数
     * @return 方法返回值
     */
    private static Object execute(Object proxy, Method method, Object[] args) {
        log.debug("执行 {} 方法", method.getName());

        if (method.isAnnotationPresent(Select.class)) {
            Select select = method.getAnnotation(Select.class);
            String sql = select.value();
            log.debug("sql: {}", sql);

            // 返回值
            Class<?> returnType = method.getReturnType();
            // 判断是否为List
            if (returnType.isAssignableFrom(List.class)) {
                return Database.manyEntryExecute(sql, returnType, args);
            }
        }

        return null;
    }
}
