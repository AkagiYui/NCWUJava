package com.akagiyui.controller;

import com.akagiyui.entity.User;
import com.akagiyui.service.UserService;
import com.dzf.framework.spring.annotation.bind.RequestMapping;
import com.dzf.framework.spring.annotation.bean.Controller;
import com.dzf.framework.spring.annotation.Autowired;
import com.dzf.framework.spring.annotation.bind.RequestParam;
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

    @RequestMapping("/user")
    public String getUser() {
        log.debug("=============controller层（查询所有用户）=============");
        List<User> users = userService.findUser();
        log.debug("users: {}", users);
        return "index.jsp";
    }

    @RequestMapping("/select")
    public String selectUser(@RequestParam("number") String number){
        log.debug("=============controller层（根据学号查询用户）=============");
        User user = userService.findUser(number);
        log.debug("user: {}", user);
        return "index.jsp";
    }
}
