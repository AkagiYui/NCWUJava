package com.dzf.framework.mvc.annotation.bean;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ResponseBody 注解
 * <p>
 * 使方法返回值自动转换为 JSON
 *
 * @author AkagiYui
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ResponseBody {
}
