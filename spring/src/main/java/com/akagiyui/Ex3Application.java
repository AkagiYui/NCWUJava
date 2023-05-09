package com.akagiyui;

import com.akagiyui.ex3.MyUserDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author AkagiYui
 */

public class Ex3Application {
    public static void main(String[] args){
        ApplicationContext context = new
                ClassPathXmlApplicationContext("MyBeansEx3.xml");

        MyUserDao userDao = context.getBean(MyUserDao.class);
        System.out.println(userDao.getClass().getName());

        userDao.addUser();
        userDao.addUserAnnotated();
    }
}
