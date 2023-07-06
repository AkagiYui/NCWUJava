package com.dzf.framework.spring.mvc;

import com.dzf.ConvertUtil;
import com.dzf.framework.spring.annotation.bind.RequestParam;
import com.dzf.framework.spring.annotation.bind.ResponseBody;
import lombok.Data;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Map;

/**
 * 请求映射处理类
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
     * 请求方法的返回值是否是ResponseBody
     */
    private boolean responseBody;

    /**
     * 请求方法的参数
     */
    private Parameter[] parameters;
    private Class<?>[] parameterTypes;

    public MvcMapping(String url, Method method, Object controller) {
        this.url = url;
        this.method = method;
        this.controller = controller;

        // 获取方法参数
        this.parameters = method.getParameters();
        this.parameterTypes = method.getParameterTypes();

        // 判断是否是ResponseBody
        this.responseBody = controller.getClass().isAnnotationPresent(ResponseBody.class)
                || method.isAnnotationPresent(ResponseBody.class);
    }

    /**
     * 调用Controller方法
     * @param params 请求参数
     * @return 请求方法的返回值
     * @throws Exception 异常
     */
    public Object invoke(Map<String, String[]> params) throws Exception {
        // 构造参数
        Object[] args = new Object[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            Parameter parameter = parameters[i];
            if (parameter.isAnnotationPresent(RequestParam.class)) {
                String paramName = parameter.getAnnotation(RequestParam.class).value();
                String[] values = params.get(paramName);
                if (values == null || values.length == 0) {
                    args[i] = null;
                } else {
                    String arg = values[0]; // 只取第一个值
                    args[i] = ConvertUtil.convert(arg, parameterTypes[i]); // 类型转换
                }
            }
        }
        return method.invoke(controller, args);
    }
}
