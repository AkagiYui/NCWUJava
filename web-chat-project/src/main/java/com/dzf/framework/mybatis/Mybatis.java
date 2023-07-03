package com.dzf.framework.mybatis;

import com.dzf.framework.XmlUtil;
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
}
