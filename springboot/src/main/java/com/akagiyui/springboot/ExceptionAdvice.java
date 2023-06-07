package com.akagiyui.springboot;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author AkagiYui
 */
@ControllerAdvice
public class ExceptionAdvice {
    @ResponseBody
    @ExceptionHandler(NullPointerException.class)
    public Map doNullPointerException(Exception e) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 500);
        map.put("message", e.getMessage());
        return map;
    }
}
