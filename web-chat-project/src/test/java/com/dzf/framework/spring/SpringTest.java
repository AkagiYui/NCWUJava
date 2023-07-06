package com.dzf.framework.spring;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author AkagiYui
 */

class SpringTest {

    @Test
    void test1() {
        try {
            //实例化文件对象
            File file = new File("D:\\AYFiles\\NCWUJava\\web-chat-project\\src\\main\\resources\\spring\\spring-config.xml");
            //解析xml对象
            SAXReader sax = new SAXReader();
            //获取dom文档
            Document doc = sax.read(file);
            //获取根标签
            Element ele = doc.getRootElement();
            //获取跟标签下的子标签
            List<Element> eleList = ele.elements();
            //遍历查看
            for (Element el:eleList) {
                //判断标签
                if("scan".equals(el.getName())){
                    //获取基础扫包
                    String val = el.attribute("base-scan").getValue();
                    System.out.println(val);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
