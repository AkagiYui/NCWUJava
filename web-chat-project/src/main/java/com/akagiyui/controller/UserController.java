package com.akagiyui.controller;

import com.akagiyui.entity.User;
import com.akagiyui.service.UserService;
import com.dzf.framework.spring.annotation.bind.RequestMapping;
import com.dzf.framework.spring.annotation.bean.Controller;
import com.dzf.framework.spring.annotation.Autowired;
import com.dzf.framework.spring.annotation.bind.RequestParam;
import com.dzf.framework.spring.annotation.bind.ResponseBody;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 用户Controller
 *
 * @author AkagiYui
 */
@Controller
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String index() {
        log.debug("=============controller层（首页）=============");
        return "index.html";
    }

    @RequestMapping("/login")
    public String login(@RequestParam("username") String number, @RequestParam("password") String password) {
        log.debug("=============controller层（登录）=============");
        log.debug("number: {}, password: {}", number, password);
        User user = userService.findUser(number, password);
        if (user == null) {
            log.debug("登录失败");
            return "401.html";
        }
        return "chat.html?username=" + number;
    }

    @RequestMapping("/test")
    @ResponseBody
    public User testResponseBody() {
        log.debug("=============controller层（测试ResponseBody注解）=============");
        User user = new User();
        user.setNumber("20170101");
        user.setNickname("AkagiYui");
        user.setPassword("123456");
        user.setGender("男");
        user.setAge(20);
        user.setBirthday("2000-01-01");
        user.setRegion("中国");
        return user;
    }

    @RequestMapping("/user")
    @ResponseBody
    public List<User> getUser() {
        log.debug("=============controller层（查询所有用户）=============");
        List<User> users = userService.findUser();
        log.debug("users: {}", users);
        return users;
    }

    @RequestMapping("/select")
    @ResponseBody
    public User selectUser(@RequestParam("number") String number) {
        log.debug("=============controller层（根据学号查询用户：{}）=============", number);
        User user = userService.findUser(number);
        log.debug("user: {}", user);
        return user;
    }
}
