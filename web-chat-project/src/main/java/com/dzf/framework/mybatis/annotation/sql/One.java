package com.dzf.framework.mybatis.annotation.sql;

/**
 * 一对一注解
 *
 * @author AkagiYui
 */
public @interface One {
    //sql语句
    String select() default "";

    // 一对多路径 包名.类名
    String path() default "";

    //外键id名 对应实体属性名
    String id() default "";
}
