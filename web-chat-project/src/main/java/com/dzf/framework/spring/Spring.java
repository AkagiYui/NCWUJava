package com.dzf.framework.spring;

import com.dzf.framework.ClassUtil;
import com.dzf.framework.spring.annotation.Autowired;
import com.dzf.framework.spring.annotation.Component;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;

/**
 * 土制 Spring 框架
 *
 * @author AkagiYui
 */
@Slf4j
public class Spring {
    /**
     * 所有 Bean 对象 {类名：实例}
     */
    private static final Map<String, Object> BEANS = new HashMap<>();
    /**
     * 标记为 Bean 的注解类 [注解, ...]
     */
    private static final List<Class<? extends Annotation>> ANNOTATIONS = new ArrayList<>();
    /** 是否已启动 */
    private static boolean isStarted = false;

    static {
        ANNOTATIONS.add(Component.class);
    }

    /**
     * 启动 Spring 框架
     *
     * @param basePackage 扫描的包名
     */
    public static void startSpring(String basePackage) {
        if (isStarted) {
            return;
        }

        ANNOTATIONS.forEach(a -> log.debug("将含有 {} 注解的类注册为 Bean", a.getName()));

        // 扫描包下所有的类
        List<Class<?>> classes = ClassUtil.getClassList(basePackage, true);
        for (Class<?> clazz : classes) {
            for (Class<? extends Annotation> annotation : ANNOTATIONS) {
                if (clazz.isAnnotationPresent(annotation)) {
                    // 如果类上有 @Component 注解，则将该类加入到 BEANS 中
                    if (BEANS.containsKey(clazz.getName()) || clazz.isAnnotation() || clazz.isEnum()) {
                        continue;
                    }
                    try {
                        log.debug("BEAN: {}", clazz.getName());
                        BEANS.put(clazz.getName(), clazz.newInstance());
                    } catch (InstantiationException | IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        log.debug("BEANS: {}", BEANS);
        dependencyInject();
        isStarted = true;
    }

    /**
     * 依赖注入
     */
    private static void dependencyInject() {
        for (Object bean : BEANS.values()) {
            // 获取所有的字段
            Field[] fields = bean.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(Autowired.class)) {
                    String beanName = field.getType().getName(); // 要注入的 Bean 的名称
                    Object targetBean = BEANS.get(beanName); // 从 BEANS 中获取要注入的 Bean
                    if (targetBean == null) {
                        throw new RuntimeException("Can not inject bean " + beanName);
                    }
                    field.setAccessible(true); // 设置字段访问权限为可写
                    try {
                        field.set(bean, targetBean); // 注入 Bean
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    log.debug("为 {} 的 {} 注入 {}", bean.getClass().getName(), field.getName(), beanName);
                }
            }
        }
    }

    /**
     * 获取 Bean
     *
     * @param clazz Bean 的类
     * @param <T>   Bean 的类型
     * @return Bean 实例
     */
    public static <T> T getBean(Class<T> clazz) {
        @SuppressWarnings("unchecked")
        T result = (T) BEANS.get(clazz.getName());
        if (result == null) {
            throw new RuntimeException("Can not get bean " + clazz.getName());
        }
        return result;
    }

    /** 添加 Bean 注解 */
    public static void addAnnotation(List<Class<? extends Annotation>> annotation) {
        annotation.forEach(a -> {
            if (!ANNOTATIONS.contains(a)) {
                ANNOTATIONS.add(a);
            }
        });
    }
}
