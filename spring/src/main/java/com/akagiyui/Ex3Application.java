package com.akagiyui;

import com.akagiyui.ex3.MyInvocationHandler;
import com.akagiyui.ex3.MyMethodInterceptor;
import com.akagiyui.ex3.MyUserDao;
import com.akagiyui.ex3.MyUserDaoImpl;
import net.sf.cglib.proxy.Enhancer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.Proxy;

/**
 * @author AkagiYui
 */

public class Ex3Application {
    public static void main(String[] args) {
        ApplicationContext context = new
                ClassPathXmlApplicationContext("MyBeansEx3.xml");

        MyUserDao userDao = context.getBean(MyUserDao.class);
        System.out.println(userDao.getClass().getName());

        // Spring AOP
        userDao.addUser();
        userDao.addUserAnnotated();

        // JDK 动态代理
        MyInvocationHandler handler = new MyInvocationHandler(userDao);
        MyUserDao proxyUserDao = (MyUserDao) Proxy.newProxyInstance(
                MyUserDao.class.getClassLoader(),
                new Class[]{MyUserDao.class},
                handler
        );
        proxyUserDao.addUser();

        // CGLIB 动态代理
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(MyUserDaoImpl.class);
        enhancer.setCallback(new MyMethodInterceptor());

        MyUserDao proxyUserDao2 = (MyUserDao) enhancer.create();
        proxyUserDao2.addUser();
    }
}
