package com.akagiyui.springbootproject.component;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 响应状态枚举
 * @author AkagiYui
 */
@Getter
@AllArgsConstructor
public enum ResponseEnum {
    /**
     * 成功
     */
    SUCCESS(10000, ""),
    /**
     * 错误
     */
    GENERAL_ERROR(10001, "General error"),
    /**
     * 内部错误
     */
    INTERNAL_ERROR(10002, "Internal error"),
    /**
     * 未找到
     */
    NOT_FOUND(10003, "Not found"),
    /**
     * 未认证
     */
    UNAUTHORIZED(10004, "Unauthorized"),
    /**
     * 存在
     */
    EXIST(10005, "Exist"),
    /**
     * 参数错误
     */
    BAD_REQUEST(10006, "Bad request"),
    /**
     * 未授权
     */
    FORBIDDEN(10007, "Forbidden"),
    /**
     * 验证码未找到
     */
    VERIFY_CODE_NOT_FOUND(10009, "Verify code not found"),
    /**
     * 请求过快
     */
    TOO_MANY_REQUESTS(10010, "Too many requests"),
    ;

    /**
     * 状态码
     */
    private final Integer code;
    /**
     * 消息
     */
    private final String msg;
}
