package com.akagiyui.ex2;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author AkagiYui
 */
@Component
public class AwareBean implements BeanNameAware, ApplicationContextAware {
    private String beanName;
    private ApplicationContext context;

    public String getBeanName() {
        return beanName;
    }

    public ApplicationContext getContext() {
        return context;
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }

    @Override
    public void setApplicationContext(ApplicationContext context) {
        this.context = context;
    }
}
