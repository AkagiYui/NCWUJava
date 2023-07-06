package com.dzf.framework.spring.mvc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 请求头部信息
 * @author AkagiYui
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MvcHeader {
    private String url;
    private String methodName;
    private Class<?>[] parameters;//参数
    private String classPath;//类路径
}
