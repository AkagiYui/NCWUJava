package com.akagiyui.ex3;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author AkagiYui
 */

@Aspect
@Component
public class MyLogAdvice {
    //切点
    @Pointcut("@annotation(com.akagiyui.ex3.MyLogAnnotation))")
    public void pointcut1(){
    }

    // 前置通知
    @Before("pointcut1()")
    public void before1(){
        System.out.println("MyLogAdvice - before");
    }

    // 后置通知
    @After("pointcut1()")
    public void after1(){
        System.out.println("MyLogAdvice - after");
    }
}
