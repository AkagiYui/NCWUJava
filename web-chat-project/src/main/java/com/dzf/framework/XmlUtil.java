package com.dzf.framework;

import org.w3c.dom.Document;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * XML解析器
 *
 * @author AkagiYui
 */
public class XmlUtil {

    /**
     * 解析XML文件
     *
     * @param filePath XML文件路径
     * @return Document对象
     */
    public static Document parse(String filePath) {
        try {
            InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath);
            if (inputStream == null) {
                throw new IOException("File not found: " + filePath);
            }
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputStream);
            doc.getDocumentElement().normalize();
            return doc;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 解析XML文件并反序列化为对象
     *
     * @param filePath XML文件路径
     * @param clazz    反序列化的对象类型
     * @param <T>      反序列化的对象类型
     * @return 反序列化后的对象
     */
    public static <T> T parse(String filePath, Class<T> clazz) {
        try {
            InputStream inputStream = Files.newInputStream(Paths.get(filePath));
            JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            return (T) jaxbUnmarshaller.unmarshal(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
