package com.akagiyui.ex2;

import org.springframework.stereotype.Repository;

/**
 * @author AkagiYui
 */
@Repository
public class MyRepository {

    public void sayHello() {
        System.out.println("MyRepository - sayHello()");
    }

}
