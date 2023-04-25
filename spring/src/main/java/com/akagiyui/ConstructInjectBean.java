package com.akagiyui;

/**
 * @author AkagiYui
 */

public class ConstructInjectBean {
    String name;

    public ConstructInjectBean(String name) {
        this.name = name;
    }

    public void sayHello() {
        System.out.println("ConstructInjectBean - sayHello() - " + name);
    }
}
