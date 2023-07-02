package com.dzf.framework.tomcat;

import lombok.Data;

import java.util.Map;

/**
 * Response 类表示一个 HTTP 响应，包括版本、状态码、状态信息、响应头和响应正文。
 */
@Data
public class Response {
    private String version;
    private int code;
    private String status;
    private Map<String, String> headers;
    private String message;
}
