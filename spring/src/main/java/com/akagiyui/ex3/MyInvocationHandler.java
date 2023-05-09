package com.akagiyui.ex3;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author AkagiYui
 */

public class MyInvocationHandler implements InvocationHandler {
    private Object target;

    public MyInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("MyInvocationHandler - before");
        Object result = method.invoke(target, args);
        System.out.println("MyInvocationHandler - after");
        return result;
    }
}
