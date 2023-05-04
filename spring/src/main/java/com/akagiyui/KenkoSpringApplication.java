package com.akagiyui;

import com.akagiyui.ex1.*;
import com.akagiyui.ex2.MyBean2;
import com.akagiyui.ex2.MyInject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author AkagiYui
 */

public class KenkoSpringApplication {
    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext(new String[] {"MyBeans.xml"});

        // 一般的Bean
        MyBean myBean = (MyBean)context.getBean("myBean");
        myBean.sayHello();

        // 静态工厂Bean
        StaticFactoryBean staticFactoryBean = (StaticFactoryBean)context.getBean("staticFactoryBean");
        staticFactoryBean.sayHello();

        // 实例工厂Bean
        InstanceFactoryBean instanceFactoryBean = (InstanceFactoryBean)context.getBean("instanceFactoryBean");
        instanceFactoryBean.sayHello();

        // 构造注入Bean
        ConstructInjectBean constructInjectBean = (ConstructInjectBean)context.getBean("constructInjectBean");
        constructInjectBean.sayHello();

        // 设值注入Bean
        SetterInjectBean setterInjectBean = (SetterInjectBean)context.getBean("setterInjectBean");
        setterInjectBean.sayHello();

        // Singleton Bean
        SingletonBean singletonBean1 = (SingletonBean)context.getBean("singletonBean");
        SingletonBean singletonBean2 = (SingletonBean)context.getBean("singletonBean");
        System.out.println("singletonBean1 == singletonBean2: " + (singletonBean1 == singletonBean2));

        // Prototype Bean
        PrototypeBean prototypeBean1 = (PrototypeBean)context.getBean("prototypeBean");
        PrototypeBean prototypeBean2 = (PrototypeBean)context.getBean("prototypeBean");
        System.out.println("prototypeBean1 == prototypeBean2: " + (prototypeBean1 == prototypeBean2));

        // 列举当前ApplicationContext中的Bean
        System.out.println("列举当前ApplicationContext中的Bean：");
        String[] beanNames = context.getBeanDefinitionNames();
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }

        /*
          实验2
         */
        MyBean2 myBean2 = context.getBean(MyBean2.class);
        myBean2.sayHello();

        MyInject myInject = context.getBean(MyInject.class);
        myInject.sayHello();
    }
}
