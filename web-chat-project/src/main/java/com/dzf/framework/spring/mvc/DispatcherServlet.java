package com.dzf.framework.spring.mvc;


import com.dzf.framework.spring.Spring;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author AkagiYui
 */
@Slf4j
public class DispatcherServlet extends GenericServlet {

    /**
     * 初始化 Servlet
     * @param config ServletConfig
     * @throws ServletException Servlet异常
     */
    @SneakyThrows
    @Override
    public void init(ServletConfig config) throws ServletException {
        log.debug("初始化 Mvc 框架");
        String configFileName = config.getInitParameter("mvcConfigPath"); // 获取初始化参数值
        List<String> packages = Spring.getScanPackages(configFileName);
        Mvc.init(packages);
    }

    /**
     * RequestMapping 分发器
     */
    @Override
    @SneakyThrows
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String requestMethod = request.getMethod(); // 请求方法
        String requestUrl = request.getRequestURI(); // 请求url
        Map<String, String[]> requestParameters = request.getParameterMap();
        log.trace("请求URL: " + requestUrl);

        // 执行方法
        MvcMapping mapping = Mvc.getMapping(requestUrl);
        if (mapping != null) {
            Object serverObj = mapping.invoke(requestParameters); // 执行方法
            log.debug("获取到的return值: " + serverObj);
            response.sendRedirect(serverObj.toString());
        } else {
            // 404 降级为静态资源请求
            Mvc.handleStaticResource(requestUrl, response);
        }
    }
}
