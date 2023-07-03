package com.dzf.framework.mybatis;

import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

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
}
