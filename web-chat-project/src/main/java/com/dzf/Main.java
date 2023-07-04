package com.dzf;

import com.dzf.framework.mvc.Mvc;
import com.dzf.framework.mybatis.Mybatis;
import com.dzf.framework.spring.Spring;
import com.dzf.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * WebSocket 聊天室
 * @author AkagiYui
 */
@Slf4j
public class Main {

    public static void main(String[] args) {
        Mvc.init("com.dzf"); // 加载 Mvc 框架
        Mybatis.init(); // 加载 Mybatis 框架
        Mybatis.scanMapper("com.dzf.mapper");
        Spring.start("com.dzf"); // 启动 Spring 框架

        UserMapper mapper = Spring.getBean(UserMapper.class);
//        UserMapper mapper = MapperProxy.getMapper(UserMapper.class);
        mapper.selectAll().forEach(
                v -> log.info(v.toString())
        );
    }
}
