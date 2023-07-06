package com.akagiyui.controller;

import com.akagiyui.entity.User;
import com.akagiyui.service.UserService;
import com.dzf.framework.spring.annotation.RequestMapping;
import com.dzf.framework.spring.annotation.bean.Controller;
import com.dzf.framework.spring.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;

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
        return "index.jsp";
    }

    @RequestMapping("/select")
    public String selectUser(String number){
        log.debug("=============controller层（根据学号查询用户）=============");
        User user = userService.findUser(number);
        log.debug("user: {}", user);
        return "index.jsp";
    }
}
