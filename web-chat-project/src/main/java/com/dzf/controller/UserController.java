package com.dzf.controller;

import com.dzf.framework.mvc.annotation.bean.Controller;
import com.dzf.framework.mvc.annotation.GetMapping;
import com.dzf.framework.spring.annotation.Autowired;
import com.dzf.service.UserService;

/**
 * @author AkagiYui
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/index")
    public void getUser() {
        userService.getUser();
    }

}
