package com.dzf.framework.spring;

import com.dzf.ClassUtil;
import com.dzf.FileUtil;
import com.dzf.framework.spring.annotation.Autowired;
import com.dzf.framework.spring.annotation.bean.Component;
import com.dzf.framework.spring.annotation.bean.Controller;
import com.dzf.framework.spring.annotation.bean.RestController;
import com.dzf.framework.spring.annotation.bean.Service;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 土制 Spring 框架
 *
 * @author AkagiYui
 */
@Slf4j
public class Spring {
    /**
     * 所有 Bean 对象 {类名：实例}
     */
    private static final Map<String, Object> BEANS = new HashMap<>();
    /**
     * 标记为 Bean 的注解类 [注解, ...]
     */
    private static final List<Class<? extends Annotation>> ANNOTATIONS = new ArrayList<>();
    /**
     * 是否已启动
     */
    private static boolean isStarted = false;

    static {
        ANNOTATIONS.add(Component.class);
        ANNOTATIONS.add(Controller.class);
        ANNOTATIONS.add(RestController.class);
        ANNOTATIONS.add(Service.class);
    }

    /**
     * 启动 Spring 框架
     *
     * @param basePackage 扫描的包名
     */
    public static void start(String basePackage) {
        if (isStarted) {
            return;
        }

        ANNOTATIONS.forEach(a -> log.debug("将含有 {} 注解的类注册为Bean", a.getName()));

        // 扫描包下所有的类
        List<Class<?>> classes = ClassUtil.getClassList(basePackage, true);
        for (Class<?> clazz : classes) {
            for (Class<? extends Annotation> annotation : ANNOTATIONS) {
                if (clazz.isAnnotationPresent(annotation)) {
                    // 如果类上有 @Component 注解，则将该类加入到 BEANS 中
                    if (BEANS.containsKey(clazz.getName()) || clazz.isAnnotation() || clazz.isEnum()) {
                        continue;
                    }
                    try {
                        log.debug("创建Bean实例: {}", clazz.getName());
                        BEANS.put(clazz.getName(), clazz.getConstructor().newInstance());
                    } catch (InstantiationException | IllegalAccessException | NoSuchMethodException |
                             InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }

        // 课程提供的代码
//        try {
//            //获取反射Class对象
//            List<String> paths = ClassUtil.listClassNamesInPackage("com.dzf");
//            for (String path : paths) {
//                Class clazz = Class.forName(path);
//                //获取注解
//                Annotation service = clazz.getAnnotation(Service.class);
//                if (service != null) {
//                    Object obj = clazz.getConstructor().newInstance();
//                    //存储@Service注解上的对象
//                    BEANS.put(clazz.getName(), obj);
//                    //获取属性
//                    Field[] fields = clazz.getDeclaredFields();
//                    for (Field field : fields) {
//                        Autowired au = field.getAnnotation(Autowired.class);
//                        if (au != null) {
//                            //赋值动态代理
//                            field.setAccessible(true);
//                            field.set(obj, DynamicAgencyFaction.getInfMapper(Class.forName(field.getGenericType().getTypeName())));
//                        }
//                    }
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        log.debug("所有Bean对象: {}", BEANS);
        dependencyInject();
        isStarted = true;
    }

    /**
     * 依赖注入
     */
    private static void dependencyInject() {
        for (Object bean : BEANS.values()) {
            // 获取所有的字段
            Field[] fields = bean.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(Autowired.class)) {
                    String beanName = field.getType().getName(); // 要注入的 Bean 的名称
                    Object targetBean = BEANS.get(beanName); // 从 BEANS 中获取要注入的 Bean
                    if (targetBean == null) {
                        continue; // 如果要注入的 Bean 不存在，则保持为 null
                    }
                    field.setAccessible(true); // 设置字段访问权限为可写
                    try {
                        field.set(bean, targetBean); // 注入 Bean
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    log.debug("为 {} 的 {} 注入 {}", bean.getClass().getName(), field.getName(), beanName);
                }
            }
        }
    }

    /**
     * 获取 Bean
     *
     * @param clazz Bean 的类
     * @param <T>   Bean 的类型
     * @return Bean 实例
     */
    public static <T> T getBean(Class<T> clazz) {
        //noinspection unchecked
        return (T) BEANS.get(clazz.getName());
    }

    public static void addBean(String name, Object bean) {
        BEANS.put(name, bean);
    }

    /**
     * 添加 Bean 注解
     *
     * @param annotation 注解
     */
    public static void addAnnotation(List<Class<? extends Annotation>> annotation) {
        annotation.forEach(a -> {
            if (!ANNOTATIONS.contains(a)) {
                ANNOTATIONS.add(a);
            }
        });
    }

    /**
     * 获取所有的 Bean
     *
     * @return 所有的 Bean
     */
    public static Map<String, Object> getBeans() {
        return BEANS;
    }

    /**
     * 获取要扫描的基础包
     * @return 基础包列表
     */
    public static List<String> getScanPackages() {
        List<String> scanPackages = new ArrayList<>();
        try {
            // 解析xml对象
            SAXReader sax = new SAXReader();
            // 获取dom文档
            Document doc = sax.read(FileUtil.getResourceFile("spring-config.xml"));
            // 获取根标签
            Element ele = doc.getRootElement();
            // 获取跟标签下的子标签
            List<Element> eleList = ele.elements();
            // 遍历查看
            for (Element el : eleList) {
                // 判断标签
                if ("scan".equals(el.getName())) {
                    // 获取基础扫包
                    String val = el.attribute("base-scan").getValue();
                    scanPackages.add(val);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return scanPackages;
    }
}
