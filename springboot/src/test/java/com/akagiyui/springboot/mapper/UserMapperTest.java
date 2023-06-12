package com.akagiyui.springboot.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author AkagiYui
 */
@SpringBootTest
class UserMapperTest {

    @Resource
    UserMapper userMapper;

    @Test
    void listUser() {
        userMapper.listUser().forEach(System.out::println);
    }
}
