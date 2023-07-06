package com.dzf.framework.spring.mvc;


import com.dzf.framework.spring.Spring;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 分发器 Servlet
 *
 * @author AkagiYui
 */
@Slf4j
public class DispatcherServlet extends GenericServlet {
    private String notFoundPage = "";

    /**
     * 初始化 Servlet
     *
     * @param config ServletConfig
     * @throws ServletException Servlet异常
     */
    @SneakyThrows
    @Override
    public void init(ServletConfig config) throws ServletException {
        log.debug("初始化 Mvc 框架");
        notFoundPage = config.getInitParameter("notFoundPage"); // 获取初始化参数值

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

        Object finished = request.getAttribute("finished");
        if (finished != null && (boolean) finished) {
            return;
        }

        String requestMethod = request.getMethod(); // 请求方法
        String requestUrl = request.getRequestURI(); // 请求url
        Map<String, String[]> requestParameters = request.getParameterMap();
        log.trace("请求URL: {}", requestUrl);

        // 执行方法
        MvcMapping mapping = Mvc.getMapping(requestUrl);
        if (mapping != null) {
            Object serverObj = mapping.invoke(requestParameters); // 执行方法
            log.debug("获取到的返回值: {}", serverObj);

            if (mapping.isResponseBody()) {
                // 如果有 @ResponseBody 注解，返回 json
                response.setContentType("application/json;charset=utf-8");
                new ObjectMapper().writeValue(response.getOutputStream(), serverObj);
            } else {
                response.sendRedirect(serverObj.toString());
            }
        } else {
            // 降级为静态资源请求
            log.trace("降级为静态资源请求");
            if (!Mvc.handleStaticResource(requestUrl, response)) {
                if (!Mvc.handleStaticResource(notFoundPage, response)) { // todo 提取到配置文件中
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                }
            }
        }

        request.setAttribute("finished", true);
    }
}
