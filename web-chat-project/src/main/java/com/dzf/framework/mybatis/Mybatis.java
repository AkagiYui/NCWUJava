package com.dzf.framework.mybatis;

import com.dzf.framework.ClassUtil;
import com.dzf.framework.XmlUtil;
import com.dzf.framework.mybatis.annotation.Mapper;
import com.dzf.framework.spring.Spring;
import org.w3c.dom.Document;

/**
 * 土制 Mybatis 框架
 *
 * @author AkagiYui
 */
public class Mybatis {
    /**
     * 初始化 Mybatis
     */
    public static void init() {
        // 从 xml 中读取配置信息
        Document doc = XmlUtil.parse("database-config.xml");
        if (doc == null) {
            return;
        }

        // 获取database节点下的driver节点的值
        String driver = XmlUtil.getValue(doc, "database", "driver");
        if (driver != null) {
            Database.DRIVER = driver;
        }

        // 获取database节点下的url节点的值
        String url = XmlUtil.getValue(doc, "database", "url");
        if (url != null) {
            Database.URL = url;
        }

        // 获取database节点下的username节点的值
        String username = XmlUtil.getValue(doc, "database", "username");
        if (username != null) {
            Database.USERNAME = username;
        }

        // 获取database节点下的password节点的值
        String password = XmlUtil.getValue(doc, "database", "password");
        if (password != null) {
            Database.PASSWORD = password;
        }

        Database.connect();
    }

    /**
     * 扫描 Mapper 并注册到 Spring 容器中
     *
     * @param basePackage 扫描的包名
     */
    public static void scanMapper(String basePackage) {
        ClassUtil.getClassList(basePackage, true).forEach(clazz -> {
            if (!clazz.isInterface()) {
                return;
            }
            if (clazz.isAnnotationPresent(Mapper.class)) {
                MapperProxy.MAPPER_BEANS.put(clazz.getName(), MapperProxy.getMapper(clazz));
                Spring.addBean(clazz.getName(), MapperProxy.getMapper(clazz));
            }
        });
    }
}
