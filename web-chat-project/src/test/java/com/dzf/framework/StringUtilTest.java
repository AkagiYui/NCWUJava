package com.dzf.framework;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * StringUtil测试类
 *
 * @author AkagiYui
 */
class StringUtilTest {
    @Test
    void testSplit() {
        String sql = "username=12&age=23";

        for (String param : sql.split("&")) {
            String[] kV = param.split("=");
            System.out.println("key:" + kV[0] + "---value:" + kV[1]);
        }
    }
}
