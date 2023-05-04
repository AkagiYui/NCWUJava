package com.akagiyui.ex2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author AkagiYui
 */
@Service
public class MyService {

    @Autowired
    MyRepository myRepository;

    public void sayHello() {
        System.out.println("MyService - sayHello()");
        myRepository.sayHello();
    }

}
