package com.akagiyui.demo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author AkagiYui
 */
@RestController
public class Ex7RestController {
    @PostMapping("/rest")
    public Result createUser(@RequestBody User user) {
        return Result.success(user);
    }
}
