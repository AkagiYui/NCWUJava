package com.akagiyui.ex1;

/**
 * @author AkagiYui
 */

public class StaticFactory {
    public static StaticFactoryBean createStaticFactoryBean() {
        System.out.println("StaticFactory - createStaticFactoryBean()");
        return new StaticFactoryBean();
    }
}
