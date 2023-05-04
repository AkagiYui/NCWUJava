package com.akagiyui;

import com.akagiyui.ex2.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author AkagiYui
 */

public class Ex2Application {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext(new String[] {"MyBeansEx2.xml"});

        MyBean2 myBean2 = context.getBean(MyBean2.class);
        myBean2.sayHello();

        MyInject myInject = context.getBean(MyInject.class);
        myInject.sayHello();

        MyController myController = context.getBean(MyController.class);
        myController.sayHello();

        ApplicationContext context2 = new AnnotationConfigApplicationContext(MyConfiguration.class);
        MyBean2 myBean22 = context2.getBean(MyBean2.class);
        myBean22.sayHello();

        LifeCycleBean lifeCycleBean = context.getBean(LifeCycleBean.class);
        lifeCycleBean.sayHello();

        context.close();
    }
}
