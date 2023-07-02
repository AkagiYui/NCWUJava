package com.dzf.framework;

import com.dzf.framework.mvc.annotation.Controller;
import org.junit.jupiter.api.Test;

/**
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
    void getClassListByAnnotation() {
        ClassUtil.getClassListByAnnotation("com.dzf", Controller.class).forEach(System.out::println);
    }
}
