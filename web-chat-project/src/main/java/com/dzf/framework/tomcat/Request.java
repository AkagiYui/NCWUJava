package com.dzf.framework.tomcat;

import lombok.Data;

import java.util.Map;

/**
 * @author AkagiYui
 */

@Data
public class Request {
    /**
     * 请求方法 GET/POST/PUT/DELETE/OPTION...
     */
    private String method;
    /**
     * 请求的uri
     */
    private String uri;
    /**
     * HTTP版本
     */
    private String version;

    /**
     * 请求头
     */
    private Map<String, String> headers;

    /**
     * 请求参数相关
     */
    private String message;
}
