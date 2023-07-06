package com.dzf.framework.spring.mvc;

import com.dzf.ClassUtil;
import com.dzf.FileUtil;
import com.dzf.framework.spring.Spring;
import com.dzf.framework.spring.annotation.bean.Controller;
import com.dzf.framework.spring.annotation.bind.RequestMapping;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Mvc 框架
 *
 * @author AkagiYui
 */
@Slf4j
public class Mvc {
    /**
     * url映射 {url:MvcMapping}
     */
    private static final Map<String, MvcMapping> MAPPING_MAP = new HashMap<>();

    @SneakyThrows
    public static void init(List<String> basePackages) {
        for (String basePackage : basePackages) {
            // 递归遍历扫描的包
            List<String> list = ClassUtil.listClassNamesInPackage(basePackage);
            for (String path : list) {
                Class<?> clazz = Class.forName(path); // 获取反射Class对象
                if (clazz.isAnnotationPresent(Controller.class)) {
                    // 获取Controller请求url前缀
                    RequestMapping classRequestMapping = clazz.getAnnotation(RequestMapping.class);
                    String urlPrefix = ""; // Controller请求url
                    if (classRequestMapping != null) {
                        urlPrefix = classRequestMapping.value();
                    }

                    // 获取Controller中所有的方法
                    Method[] methods = clazz.getDeclaredMethods();
                    for (Method method : methods) {
                        RequestMapping methodRequestMapping = method.getAnnotation(RequestMapping.class);
                        if (methodRequestMapping == null) {
                            continue;
                        }
                        String url = urlPrefix + methodRequestMapping.value(); // 完整url
                        String methodName = method.getName(); // 方法名
                        Class<?>[] parameters = method.getParameterTypes(); // 参数类型
                        Class<?> returnType = method.getReturnType(); // 返回值类型

                        Object controller = Spring.getBean(clazz); // 从 Spring 中获取 Controller 实例
                        MvcMapping model = new MvcMapping(url, method, controller);
                        MAPPING_MAP.put(url, model);
                    }
                }
            }
        }
    }

    /**
     * 处理静态资源
     * @param resourceName 静态资源名称
     * @param response 响应
     */
    public static void handleStaticResource(String resourceName, HttpServletResponse response) {
        try {
            resourceName = FileUtil.getStaticResourcePath(resourceName); // 获取静态资源路径
            InputStream is = Files.newInputStream(Path.of(resourceName)); // 获取静态资源的输入流
            String contentType = URLConnection.guessContentTypeFromName(resourceName); // 设置响应头
            response.setContentType(contentType); // 设置内容类型
            OutputStream os = response.getOutputStream(); // 将静态资源写入响应
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.flush();
            os.close();
            is.close();
        } catch (NoSuchFileException e) {
            log.warn("找不到静态资源: {}", resourceName);
            try {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
            try {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    public static MvcMapping getMapping(String url) {
        return MAPPING_MAP.get(url);
    }
}
