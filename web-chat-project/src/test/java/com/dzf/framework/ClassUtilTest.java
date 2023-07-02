package com.dzf.framework;

import org.junit.jupiter.api.Test;

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

}
