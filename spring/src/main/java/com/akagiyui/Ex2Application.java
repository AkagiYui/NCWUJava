package com.akagiyui;

import com.akagiyui.ex2.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Objects;

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

        AwareBean awareBean = context.getBean(AwareBean.class);
        System.out.println(awareBean.getBeanName());
        System.out.println(awareBean.getContext());
        System.out.println("awareBean.context == context: " + Objects.equals(awareBean.getContext(), context));

        PrototypeBean prototypeBean1 = context.getBean(PrototypeBean.class);
        PrototypeBean prototypeBean2 = context.getBean(PrototypeBean.class);
        System.out.println("prototypeBean1 == prototypeBean2: " + Objects.equals(prototypeBean1, prototypeBean2));

        context.close();

        ApplicationContext context3 = new AnnotationConfigApplicationContext(ConfigB.class);
        ConfigA.A a = context3.getBean(ConfigA.A.class);
        ConfigB.B b = context3.getBean(ConfigB.B.class);
        a.sayHello();
        b.sayHello();
    }
}
