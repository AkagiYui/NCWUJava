package com.akagiyui.ex2;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @author AkagiYui
 */
@Component
public class LifeCycleBean implements InitializingBean, DisposableBean {
    @Override
    public void afterPropertiesSet() {
        System.out.println("InitializingBean.afterPropertiesSet()");
    }

    @Override
    public void destroy() {
        System.out.println("DisposableBean.destroy()");
    }

    public void sayHello() {
        System.out.println("LifeCycleBean - sayHello()");
    }
}
