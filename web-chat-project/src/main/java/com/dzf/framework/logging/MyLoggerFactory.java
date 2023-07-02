package com.dzf.framework.logging;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

/**
 * 自定义 Logger 工厂类
 *
 * @author AkagiYui
 */
public class MyLoggerFactory implements ILoggerFactory {

    @Override
    public Logger getLogger(String name) {
        return new MyLogger(name);
    }

}
