package com.akagiyui.ex2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author AkagiYui
 */
@Controller
public class MyController {

    @Autowired
    MyService myService;

    public void sayHello() {
        System.out.println("MyController - sayHello()");
        myService.sayHello();
    }

}
