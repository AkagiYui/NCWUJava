package com.akagiyui;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author AkagiYui
 */

public class KenkoSpringApplication {
    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext(new String[] {"MyBeans.xml"});

        MyBean myBean = (MyBean)context.getBean("myBean");
        myBean.sayHello();

        StaticFactoryBean staticFactoryBean = (StaticFactoryBean)context.getBean("staticFactoryBean");
        staticFactoryBean.sayHello();

        // 列举当前ApplicationContext中的Bean
        System.out.println("列举当前ApplicationContext中的Bean：");
        String[] beanNames = context.getBeanDefinitionNames();
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }
    }
}
