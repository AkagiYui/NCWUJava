package com.dzf.framework;

import java.io.File;
import java.lang.annotation.Annotation;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collectors;

/**
 * 类操作工具类
 *
 * @author AkagiYui
 */
public class ClassUtil {
    /**
     * 获取指定包名下的所有类
     *
     * @param packageName 包名
     * @param isRecursive 是否递归
     * @return 类集合
     */
    public static List<Class<?>> getClassList(String packageName, boolean isRecursive) {
        List<Class<?>> classList = new ArrayList<>();
        try {
            Enumeration<URL> urls = Thread.currentThread().getContextClassLoader().getResources(packageName.replaceAll("\\.", "/"));
            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                if (url != null) {
                    String protocol = url.getProtocol();
                    switch (protocol) {
                        case "file":
                            String packagePath = url.getPath();
                            addClass(classList, packagePath, packageName, isRecursive);
                            break;
                        case "jar":
                            JarURLConnection jarUrlConnection = (JarURLConnection) url.openConnection();
                            JarFile jarFile = jarUrlConnection.getJarFile();
                            Enumeration<JarEntry> jarEntries = jarFile.entries();
                            while (jarEntries.hasMoreElements()) {
                                JarEntry jarEntry = jarEntries.nextElement();
                                String jarEntryName = jarEntry.getName();
                                if (jarEntryName.endsWith(".class")) {
                                    String className = jarEntryName.substring(0, jarEntryName.lastIndexOf(".")).replaceAll("/", ".");
                                    if (isRecursive || className.substring(0, className.lastIndexOf(".")).equals(packageName)) {
                                        classList.add(Class.forName(className));
                                    }
                                }
                            }
                            break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return classList;
    }

    /**
     * 获取指定包名下的所有注解类
     *
     * @param packageName 包名
     * @return 类集合
     */
    public static List<Class<? extends Annotation>> getAnnotationList(String packageName) {
        @SuppressWarnings("unchecked")
        List<Class<? extends Annotation>> annotationList = getClassList(packageName, true)
                .stream()
                .filter(Class::isAnnotation)
                .map(c -> (Class<? extends Annotation>) c)
                .collect(Collectors.toList());
        return annotationList;
    }

    /**
     * 向 classList 中添加类
     *
     * @param classList   类集合
     * @param packagePath 包路径
     * @param packageName 包名
     * @param isRecursive 是否递归
     */
    private static void addClass(List<Class<?>> classList, String packagePath, String packageName, boolean isRecursive) {
        try {
            File[] files = getClassFiles(packagePath);
            if (files != null) {
                for (File file : files) {
                    String fileName = file.getName();
                    if (file.isFile()) {
                        String className = getClassName(packageName, fileName);
                        classList.add(Class.forName(className));
                    } else {
                        if (isRecursive) {
                            String subPackagePath = getSubPackagePath(packagePath, fileName);
                            String subPackageName = getSubPackageName(packageName, fileName);
                            addClass(classList, subPackagePath, subPackageName, true);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取指定包路径下的所有类文件
     *
     * @param packagePath 包路径
     * @return 类文件集合
     */
    private static File[] getClassFiles(String packagePath) {
        return new File(packagePath).listFiles(file ->
                (file.isFile() && file.getName().endsWith(".class"))
                        || file.isDirectory()
        );
    }

    /**
     * 获取类名
     *
     * @param packageName 包名
     * @param fileName    文件名
     * @return 类名
     */
    private static String getClassName(String packageName, String fileName) {
        String className = fileName.substring(0, fileName.lastIndexOf("."));
        if (StringUtil.isNotEmpty(packageName)) {
            className = packageName + "." + className;
        }
        return className;
    }

    /**
     * 获取子包路径
     *
     * @param packagePath 包路径
     * @param filePath    文件路径
     * @return 子包路径
     */
    private static String getSubPackagePath(String packagePath, String filePath) {
        String subPackagePath = filePath;
        if (StringUtil.isNotEmpty(packagePath)) {
            subPackagePath = packagePath + "/" + subPackagePath;
        }
        return subPackagePath;
    }

    /**
     * 获取子包名
     *
     * @param packageName 包名
     * @param filePath    文件路径
     * @return 子包名
     */
    private static String getSubPackageName(String packageName, String filePath) {
        String subPackageName = filePath;
        if (StringUtil.isNotEmpty(packageName)) {
            subPackageName = packageName + "." + subPackageName;
        }
        return subPackageName;
    }

}
