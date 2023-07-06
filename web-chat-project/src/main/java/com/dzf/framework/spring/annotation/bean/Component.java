package com.dzf.framework.spring.annotation.bean;

import java.lang.annotation.*;

/**
 * 自定义 Component 注解
 * 添加了该注解的类会被 Spring 容器管理
 *
 * @author AkagiYui
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Component {
}
