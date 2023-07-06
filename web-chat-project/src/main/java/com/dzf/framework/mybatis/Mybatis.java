package com.dzf.framework.mybatis;

import com.dzf.ClassUtil;
import com.dzf.FileUtil;
import com.dzf.XmlUtil;
import com.dzf.framework.mybatis.annotation.Mapper;
import com.dzf.framework.mybatis.annotation.sql.Select;
import com.dzf.framework.mybatis.mapperAgency.DynamicAgencyFactory;
import com.dzf.framework.mybatis.pojo.MapperQuery;
import com.dzf.framework.spring.Spring;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Attribute;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.w3c.dom.Document;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 土制 Mybatis 框架
 *
 * @author AkagiYui
 */
@Slf4j
public class Mybatis {
    /**
     * 初始化 Mybatis
     */
    public static void init() {
        // 从 xml 中读取配置信息
        Document doc = XmlUtil.parse(FileUtil.getResourceFile("database-config.xml"));
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

        scanMapper("com.akagiyui"); // todo
        Database.connect();
    }

    /**
     * 扫描 Mapper 并注册到 Spring 容器中
     *
     * @param basePackage 扫描的包名
     */
    public static void scanMapper(String basePackage) {
        for (Class<?> clazz : ClassUtil.getClassList(basePackage, true)) {
            if (!clazz.isInterface()) {
                continue;
            }
            if (clazz.isAnnotationPresent(Mapper.class)) {
                // MapperProxy.MAPPER_BEANS.put(clazz.getName(), MapperProxy.getMapper(clazz));
                // Spring.addBean(clazz.getName(), MapperProxy.getMapper(clazz));
                try {
                    Spring.addInterface(clazz, DynamicAgencyFactory.class.getMethod("getInfMapper", Class.class));
                } catch (NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static com.dzf.framework.mybatis.pojo.Mapper parseMapperFile(File file) throws Exception {
        com.dzf.framework.mybatis.pojo.Mapper resultMapper = new com.dzf.framework.mybatis.pojo.Mapper();
        List<MapperQuery> resultMapperQueries = new ArrayList<>();

        //先实例化SAXReader对象
        SAXReader reader = new SAXReader();
        //通过SAXReader对象读取xml文件 返回Document文档
        org.dom4j.Document document = reader.read(file);
        Element root = document.getRootElement();
        //通过Element获取标签的属性值
        Attribute attr = root.attribute("namespace");
        String attrValue = attr.getValue();
        log.debug("namespace:" + attrValue);
        resultMapper.setNamespace(attrValue);
        //获取子标签元素
        List<Element> list = root.elements();
        for (Element el : list) {
            if (el.isTextOnly()) {
                MapperQuery mapperQuery = new MapperQuery();
                List<String> params = new ArrayList<>();

                //获取sql
                String sql = el.getText();
                log.debug("获取的sql:" + sql);

                //使用正则
                Matcher matcher = Pattern.compile("#\\{([^}]+)}").matcher(sql);
                while (matcher.find()) {
                    //匹配到的变量名
                    String val = matcher.group(1);
                    params.add(val);
                    log.debug("参数值:" + val);
                }

                //sql修正 预编译形式
                sql = sql.replaceAll("#\\{([^}]+)}", "?");
                log.debug("预编译sql:" + sql);
                mapperQuery.setSql(sql);

                // 获取 id 属性
                Attribute idAttr = el.attribute("id");
                String idAttrValue = idAttr.getValue();
                log.debug("id:" + idAttrValue);
                mapperQuery.setId(idAttrValue);

                // 获取 resultType 属性
                Attribute resultTypeAttr = el.attribute("resultType");
                String resultTypeAttrValue = resultTypeAttr.getValue();
                log.debug("resultType:" + resultTypeAttrValue);
                mapperQuery.setResultType(Class.forName(resultTypeAttrValue));

                mapperQuery.setParams(params.toArray(new String[0]));
                resultMapperQueries.add(mapperQuery);
            }
        }

        resultMapper.setQueries(resultMapperQueries.toArray(new MapperQuery[0]));
        return resultMapper;
    }

    public static Map<String, MapperQuery> parseMapperClass(String packageName) throws Exception {
        Map<String, MapperQuery> mapper = new HashMap<>();
        for (String path : ClassUtil.listClassNamesInPackage(packageName)) {
            //获取反射Class对象
            Class<?> clazz = Class.forName(path);
            if (clazz.isAnnotationPresent(Mapper.class)) {
                //获取方法
                Method[] methods = clazz.getMethods();
                for (Method method : methods) {
                    Select select = method.getAnnotation(Select.class);
                    if (select != null) {
                        //实例化
                        MapperQuery ms = new MapperQuery(select.value(), select.one());
                        String key = clazz.getName() + "." + method.getName();
                        mapper.put(key, ms);
                    }
                }
            }
        }
        return mapper;
    }
}
