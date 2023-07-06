package com.akagiyui.service;

import com.akagiyui.entity.User;
import com.akagiyui.mapper.UserMapper;
import com.dzf.framework.spring.annotation.bean.Service;
import com.dzf.framework.spring.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 用户 业务
 *
 * @author AkagiYui
 */
@Service
@Slf4j
public class UserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 根据学号查询用户
     *
     * @param number 学号
     * @return 用户
     */
    public User findUser(String number) {
        log.debug("=============service层（根据学号查询用户）=============");
        return userMapper.selectUser(number);
    }

    /**
     * 根据学号和密码查询用户
     *
     * @param number 学号
     * @param password 密码
     * @return 用户
     */
    public User findUser(String number, String password) {
        log.debug("=============service层（根据学号和密码查询用户）=============");
        return userMapper.selectUser(number, password);
    }

    /**
     * 查询所有用户
     *
     * @return 用户列表
     */
    public List<User> findUser() {
        log.debug("=============service层（查询所有用户）=============");
        return userMapper.selectUser();
    }
}
