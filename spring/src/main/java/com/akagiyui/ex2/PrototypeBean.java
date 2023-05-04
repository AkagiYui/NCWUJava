package com.akagiyui.ex2;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author AkagiYui
 */
@Component
@Scope("prototype")
public class PrototypeBean {
    public void sayHello() {
        System.out.println("PrototypeBean - sayHello()");
    }
}
