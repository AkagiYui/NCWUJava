package com.dzf.framework.spring;


import com.dzf.framework.mybatis.Mybatis;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

import java.util.List;


/**
 * ServletContext监听器，在 Servlet初始化时 初始化spring
 * @author AkagiYui
 */
public class ServletListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Mybatis.init(); // 初始化 Mybatis

        ServletContext sc = sce.getServletContext(); // 获取上下文对象
        String configFileName = sc.getInitParameter("springConfigPath"); // 获取监听初始化值
        List<String> packages = Spring.getScanPackages(configFileName);// 初始化spring
        Spring.start(packages);
    }
}
