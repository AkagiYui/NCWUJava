package com.akagiyui.ex3;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author AkagiYui
 */

public class MyMethodInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("MyMethodInterceptor - before");
        Object result = proxy.invokeSuper(obj, args);
        System.out.println("MyMethodInterceptor - after");
        return result;
    }
}
