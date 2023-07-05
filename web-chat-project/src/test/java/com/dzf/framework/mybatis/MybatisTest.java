package com.dzf.framework.mybatis;

import com.dzf.FileUtil;
import com.dzf.framework.mybatis.pojo.Mapper;
import org.dom4j.Attribute;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author AkagiYui
 */

class MybatisTest {

    @Test
    void init() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File("src/main/resources/database-config.xml"));

        Element databaseElement = (Element) document.getElementsByTagName("database").item(0);
        String driver = databaseElement.getElementsByTagName("driver").item(0).getTextContent();
        String url = databaseElement.getElementsByTagName("url").item(0).getTextContent();
        String username = databaseElement.getElementsByTagName("username").item(0).getTextContent();

        System.out.println("driver: " + driver);
        System.out.println("url: " + url);
        System.out.println("username: " + username);
    }

    @Test
    void test1() throws DocumentException {
        //先实例化SAXReader对象
        SAXReader reader = new SAXReader();
        //通过SAXReader对象读取xml文件 返回Document文档
        org.dom4j.Document document = reader.read(FileUtil.getResourceFile("mybatis/GoodMapper.xml"));
        org.dom4j.Element root = document.getRootElement();
        //通过Element获取标签的属性值
        Attribute attr = root.attribute("namespace");
        String attrValue = attr.getValue();
        System.out.println("命名空间值:"+attrValue);
        //获取子标签元素
        List<org.dom4j.Element> list = root.elements();
        for (org.dom4j.Element el:list){
            if(el.isTextOnly()){
                //获取sql
                String sql = el.getText();
                System.out.println("获取的sql:"+sql);
                //使用正则
                Matcher matcher = Pattern.compile("#\\{([^}]+)}").matcher(sql);
                while (matcher.find()){
                    //匹配到的变量名
                    String val = matcher.group(1);
                    System.out.println("参数值:"+val);
                }
                //sql修正 预编译形式
                sql = sql.replaceAll("#\\{([^}]+)}","?");
                System.out.println("预编译sql:"+sql);
            }
        }
    }

    @Test
    void parseMapper() throws Exception {
        Mapper mapper = Mybatis.parseMapperFile(FileUtil.getResourceFile("mapper/GoodMapper.xml"));
        System.out.println(mapper);
    }
}
