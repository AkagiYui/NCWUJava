package com.dzf.framework.logging;

import org.slf4j.ILoggerFactory;
import org.slf4j.IMarkerFactory;
import org.slf4j.spi.MDCAdapter;
import org.slf4j.spi.SLF4JServiceProvider;

/**
 * 自定义 SLF4J Service Provider 实现类
 * <p>
 * 适配 SLF4J
 *
 * @author AkagiYui
 */
public class MySLF4JServiceProvider implements SLF4JServiceProvider {
    @Override
    public ILoggerFactory getLoggerFactory() {
        return new MyLoggerFactory();
    }

    @Override
    public IMarkerFactory getMarkerFactory() {
        return null;
    }

    @Override
    public MDCAdapter getMDCAdapter() {
        return null;
    }

    @Override
    public String getRequestedApiVersion() {
        return "2.0.0";
    }

    @Override
    public void initialize() {

    }
}
