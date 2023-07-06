package com.dzf.framework.spring.annotation.bean;

import com.dzf.framework.spring.annotation.bind.ResponseBody;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * RestController 注解
 * <p>
 * 使 Controller 返回值自动转换为 JSON
 *
 * @author AkagiYui
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Controller
@ResponseBody
public @interface RestController {
}
