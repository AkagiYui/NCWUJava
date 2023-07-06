package com.dzf.framework.spring.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * @author AkagiYui
 */
@Target(ElementType.METHOD)
public @interface GetMapping {
    String value() default  "";
}
