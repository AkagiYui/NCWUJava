package com.dzf;

import com.dzf.framework.spring.mvc.Mvc;
import com.dzf.framework.mybatis.Mybatis;
import com.dzf.framework.spring.Spring;
import com.akagiyui.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author AkagiYui
 */
@Slf4j
public class MainTest {
    @Test
    void test() {
        Mvc.init(List.of("com.akagiyui")); // 加载 Mvc 框架
        Mybatis.init(); // 加载 Mybatis 框架
        Mybatis.scanMapper("com.akagiyui.mapper");
        //Spring.start("com.akagiyui"); // 启动 Spring 框架

        UserMapper mapper = Spring.getBean(UserMapper.class);
//        UserMapper mapper = MapperProxy.getMapper(UserMapper.class);
        mapper.selectUser().forEach(
                v -> log.info(v.toString())
        );
    }
}
