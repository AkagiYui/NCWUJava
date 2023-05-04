package com.akagiyui.ex2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author AkagiYui
 */
@Configuration
@Import({ConfigA.class})
public class ConfigB {
    @Bean
    public B b() {
        return new B();
    }

    public static class B {
        public void sayHello() {
            System.out.println("B - sayHello()");
        }
    }
}
