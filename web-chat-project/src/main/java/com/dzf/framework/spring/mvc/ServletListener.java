package com.dzf.framework.spring.mvc;


import com.dzf.FileUtil;
import com.dzf.framework.spring.Spring;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;


/**
 * ServletContext监听器，在 Servlet初始化时 初始化spring
 * @author AkagiYui
 */
public class ServletListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext sc = sce.getServletContext(); // 获取上下文对象
        String configValue = sc.getInitParameter("springConfigPath"); // 获取监听初始化值
        configValue = FileUtil.getResourcePath(configValue); // 获取核心xml文件配置
        Spring.start(configValue); // 启动spring
    }
}
