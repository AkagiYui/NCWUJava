package com.akagiyui.ex3;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author AkagiYui
 */

@Aspect
@Component
public class MyAspect {
    //切点
    @Pointcut("execution( * com.akagiyui.ex3.MyUserDaoImpl.*(..))")
    public void pointcut1(){
    }

    //前置通知
    @Before("pointcut1()")
    public void before1(JoinPoint joinPoint){
        System.out.println("AnnoAdvice - before1:");
        System.out.println("    target object: " + joinPoint.getTarget());
        System.out.println("    target method: "+ joinPoint.getSignature().getName());
    }

    // 后置通知
    @After("pointcut1()")
    public void after1(){
        System.out.println("AnnoAdvice - after");
    }

    // 异常通知
    @AfterThrowing("pointcut1()")
    public void afterThrowing1(){
        System.out.println("AnnoAdvice - afterThrowing");
    }
}
