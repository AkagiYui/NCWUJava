package com.akagiyui.ex2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


/**
 * @author AkagiYui
 */
@Component
public class MyInject {
    @Autowired
    MyBean2 myBeanByAutowired;

    @Resource
    MyBean2 myBeanByResource;

    public void sayHello() {
        System.out.println("MyInject - sayHello()");

        System.out.println("MyInject - CALL myBeanByAutowired.sayHello()");
        myBeanByAutowired.sayHello();

        System.out.println("MyInject - CALL myBeanByResource.sayHello()");
        myBeanByResource.sayHello();
    }
}
