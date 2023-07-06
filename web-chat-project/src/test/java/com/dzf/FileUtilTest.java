package com.dzf;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

/**
 * @author AkagiYui
 */

class FileUtilTest {

    @Test
    void getResourceFile() throws IOException {
        File file = FileUtil.getResourceFile("spring-config.xml");
        // 输出文件文本内容
        FileUtil.readFileToString(file).lines().forEach(System.out::println);
    }
}
