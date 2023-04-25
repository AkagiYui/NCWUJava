package com.akagiyui;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author AkagiYui
 */

public class KenkoSpringApplication {
    public static void main(String[] args)	{
        ApplicationContext context =
                new ClassPathXmlApplicationContext(new String[] {"MyBeans.xml"});

        MyBean myBean = (MyBean)context.getBean("myBean");
        myBean.sayHello();
    }
}
