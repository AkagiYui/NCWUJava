package com.dzf.framework.mvc;

import com.dzf.framework.ClassUtil;
import com.dzf.framework.spring.Spring;
import com.dzf.framework.spring.annotation.Component;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

/**
 * Mvc 框架
 *
 * @author AkagiYui
 */
public class Mvc {
    static {
        // 将 mvc 包下的注解类加入到 ANNOTATIONS 中
        List<Class<? extends Annotation>> newAnnotations = new ArrayList<>();
        ClassUtil.getAnnotationList("com.dzf.framework.mvc.annotation.bean").forEach(v -> {
            if (v.isAnnotationPresent(Component.class)) {
                newAnnotations.add(v);
            }
        });
        Spring.addAnnotation(newAnnotations);
    }

    public static void init(String... basePackage) {
    }
}
