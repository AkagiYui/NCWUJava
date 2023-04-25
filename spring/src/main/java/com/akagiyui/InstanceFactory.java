package com.akagiyui;

/**
 * @author AkagiYui
 */

public class InstanceFactory {
    public InstanceFactoryBean createInstanceFactoryBean() {
        System.out.println("InstanceFactory - createInstanceFactoryBean()");
        return new InstanceFactoryBean();
    }
}
