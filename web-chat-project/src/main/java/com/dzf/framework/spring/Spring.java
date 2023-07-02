package com.dzf.framework.spring;

import com.dzf.framework.ClassUtil;
import com.dzf.framework.spring.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 土制 Spring 框架
 * @author AkagiYui
 */
@Slf4j
public class Spring {
    /** 存放所有的对象 {类名：实例} */
    public static final Map<String, Object> BEANS = new HashMap<>();
    /** 存放注解类 [注解] */
    public static final List<Class<? extends Annotation>> ANNOTATIONS = new ArrayList<>();

    public static void startSpring() {
        List<Class<?>> classes = ClassUtil.getClassList("com.dzf", true);
        for (Class<?> clazz : classes) {

            for (Class<? extends Annotation> annotation : ANNOTATIONS) {
                if (clazz.isAnnotationPresent(annotation)) {
                    try {
                        BEANS.put(clazz.getName(), clazz.newInstance());
                    } catch (InstantiationException | IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        System.out.println(BEANS);
        autoInject();
    }

    private static void autoInject() {
        for (Object bean : BEANS.values()) {
            // 获取所有的字段
            Field[] fields = bean.getClass().getDeclaredFields();
            for (Field field : fields) {
                // 判断字段是否有 @Autowired 注解
                if (field.isAnnotationPresent(Autowired.class)) {
                    // 获取要注入的 Bean 的名称
                    String beanName = field.getType().getName();
                    // 从 BEANS 中获取要注入的 Bean
                    Object targetBean = BEANS.get(beanName);
                    if (targetBean == null) {
                        // 如果 BEANS 中没有要注入的 Bean，则抛出异常
                        throw new RuntimeException("Can not inject bean " + beanName);
                    }
                    // 设置字段访问权限为可写
                    field.setAccessible(true);
                    try {
                        // 注入 Bean
                        field.set(bean, targetBean);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    log.debug("为 {} 的 {} 注入 {}", bean.getClass().getName(), field.getName(), beanName);
                }
            }
        }
    }

    public static void main(String[] args) {
        startSpring();
    }
}
