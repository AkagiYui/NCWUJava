package com.dzf.framework;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Enumeration;
import java.util.List;

/**
 * ClassUtil测试类
 * @author AkagiYui
 */
class ClassUtilTest {

    @Test
    void getClassList() {
        ClassUtil.getClassList("com.dzf", true).forEach(v -> {
            if (v.isInterface()) {
                System.out.println(v);
            }
        });
    }

    @Test
    void findClassNamesInPackage() throws URISyntaxException, IOException {
        //获取路径
        Enumeration<URL> dirs = Thread.currentThread().getContextClassLoader().getResources("");
        while (dirs.hasMoreElements()) {
            //获取服务上下文路径
            String contextPath = dirs.nextElement().toURI().toString();
            //把file:/去掉
            contextPath = contextPath.replace("file:/", "");
            //覆盖上次
            String canonicalPath = "com.dzf";
            //扫描路径
            canonicalPath = canonicalPath.replaceAll("\\.", "/");
            canonicalPath = contextPath.concat(canonicalPath);
            //声明文件对象
            File file = new File(canonicalPath);
            //递归遍历扫描的包
            List<String> list = ClassUtil.listClassNamesInPackage(file, contextPath);
            list.forEach(System.out::println);
        }
    }

    @Test
    void findClassNamesInPackage2() {
        ClassUtil.listClassNamesInPackage("com.dzf").forEach(System.out::println);
    }
}
