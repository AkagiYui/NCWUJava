package com.dzf.framework.mybatis;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * Mapper 代理
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
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, (proxy, method, args) -> {
            log.debug(method.getName());
            return null;
        });
    }

}
