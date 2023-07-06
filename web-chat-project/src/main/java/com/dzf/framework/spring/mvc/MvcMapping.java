package com.dzf.framework.spring.mvc;

import lombok.Data;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Map;

/**
 * 请求头部信息
 *
 * @author AkagiYui
 */
@Data
public class MvcMapping {
    /**
     * 请求url
     */
    private String url;
    /**
     * 请求方法
     */
    private Method method;
    /**
     * 请求方法所在的Controller
     */
    private Object controller;

    /**
     * 请求方法的参数
     */
    private Parameter[] parameters;
    private Class<?>[] parameterTypes;

    public MvcMapping(String url, Method method, Object controller) {
        this.url = url;
        this.method = method;
        this.controller = controller;

        this.parameters = method.getParameters();
        this.parameterTypes = method.getParameterTypes();
    }

    public Object invoke(Map<String, String[]> params) throws Exception {
        // 构造参数
        Object[] args = new Object[parameterTypes.length];
        return method.invoke(controller, args);
    }
}
