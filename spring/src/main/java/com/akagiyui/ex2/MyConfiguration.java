package com.akagiyui.ex2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author AkagiYui
 */
@Configuration
public class MyConfiguration {

    @Bean
    public MyBean2 myBean2() {
        System.out.println("MyConfiguration - myBean2()");
        return new MyBean2();
    }

}
