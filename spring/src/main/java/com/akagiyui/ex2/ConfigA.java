package com.akagiyui.ex2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author AkagiYui
 */
@Configuration
public class ConfigA {
    @Bean
    public A a() {
        return new A();
    }

    public static class A {
        public void sayHello() {
            System.out.println("A - sayHello()");
        }
    }
}
