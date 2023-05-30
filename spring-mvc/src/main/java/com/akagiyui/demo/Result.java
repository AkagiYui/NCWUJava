package com.akagiyui.demo;

import lombok.Data;

/**
 * @author AkagiYui
 */
@Data
public class Result {
    private int code;
    private String message;
    private Object data;

    public static Result success(Object data) {
        Result result = new Result();
        result.setCode(200);
        result.setMessage("success");
        result.setData(data);
        return result;
    }
}
