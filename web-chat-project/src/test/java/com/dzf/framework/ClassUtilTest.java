package com.dzf.framework;

import com.dzf.ClassUtil;
import org.junit.jupiter.api.Test;

/**
 * ClassUtil测试类
 *
 * @author AkagiYui
 */
class ClassUtilTest {

    @Test
    void getClassList() {
        ClassUtil.getClassList("com.akagiyui", true).forEach(v -> {
            if (v.isInterface()) {
                System.out.println(v);
            }
        });
    }

    @Test
    void findClassNamesInPackage() {
        ClassUtil.listClassNamesInPackage("com.akagiyui").forEach(System.out::println);
    }
}
