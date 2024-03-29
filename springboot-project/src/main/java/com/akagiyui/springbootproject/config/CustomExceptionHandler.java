package com.akagiyui.springbootproject.config;


import com.akagiyui.springbootproject.entity.ResponseResult;
import com.akagiyui.springbootproject.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import static com.akagiyui.springbootproject.component.ResponseEnum.*;


/**
 * 全局异常处理器
 *
 * @author AkagiYui
 */
@RestControllerAdvice
public class CustomExceptionHandler {

    /**
     * 404 异常处理
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({
            NoHandlerFoundException.class,
            HttpRequestMethodNotSupportedException.class,
    })
    public ResponseResult<?> noRouteException(Exception ignored) {
        return ResponseResult.response(NOT_FOUND);
    }

    /**
     * 400 请求体错误异常处理
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({HttpMessageNotReadableException.class, MissingServletRequestParameterException.class})
    public ResponseResult<?> jsonParseException(Exception e) {
        // 目前可预见的是 JSON 解析错误
        Throwable cause = e.getCause();
        if (cause != null) {
            return ResponseResult.response(BAD_REQUEST, cause.getMessage());
        }
        // 无请求体错误
        if (e.getMessage() != null && e.getMessage().startsWith("Required request body is missing")) {
            return ResponseResult.response(BAD_REQUEST, "Request body is missing");
        }
        return ResponseResult.response(BAD_REQUEST, e.getMessage());
    }

    /**
     * 其他异常处理
     */
    @ExceptionHandler(Exception.class)
    public ResponseResult<?> unknownException(Exception e) {
        // 自定义异常处理
        if (e instanceof CustomException) {
            CustomException ce = (CustomException) e;
            return ResponseResult.response(ce.getStatus());
        }
        // 参数校验异常处理
        if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException ae = (MethodArgumentNotValidException) e;
            FieldError fieldError = ae.getBindingResult().getFieldError();
            if (fieldError != null) {
                return ResponseResult.response(BAD_REQUEST, fieldError.getDefaultMessage());
            }
            return ResponseResult.response(BAD_REQUEST);
        }
        e.printStackTrace();
        return ResponseResult.response(INTERNAL_ERROR);
    }
}
