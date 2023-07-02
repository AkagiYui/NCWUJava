package com.dzf.framework.logging;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

/**
 * @author AkagiYui
 */
public class MyLoggerFactory implements ILoggerFactory {

    @Override
    public Logger getLogger(String name) {
        return new MyLogger(name);
    }

    // 静态方法，用于返回一个 ILoggerFactory 实例
    public static ILoggerFactory getLoggerFactory() {
        return new MyLoggerFactory();
    }
}
