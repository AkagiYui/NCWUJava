package com.akagiyui;

/**
 * @author AkagiYui
 */

public class SetterInjectBean {
    String name;

    public void setName(String name) {
        this.name = name;
    }

    public void sayHello() {
        System.out.println("SetterInjectBean - sayHello() - " + name);
    }
}
