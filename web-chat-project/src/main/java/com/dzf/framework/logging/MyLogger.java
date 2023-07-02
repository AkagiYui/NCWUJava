package com.dzf.framework.logging;

import com.dzf.framework.StringUtil;
import com.dzf.framework.XmlUtil;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.w3c.dom.Document;

/**
 * 自定义 Logger 实现类
 *
 * @author AkagiYui
 */
public class MyLogger implements Logger {
    /**
     * Logger 名称
     */
    private final String name;
    /**
     * 日志级别
     */
    private int level = 0;

    public MyLogger(String name) {
        this.name = name;

        Document doc = XmlUtil.parse("log-config.xml");
        if (doc != null) {
            // 获取logger节点下的level节点的值
            String level = doc.getElementsByTagName("level").item(0).getTextContent();
            if (level == null) {
                return;
            }
            level = level.toUpperCase();
            switch (level) {
                case "DEBUG":
                    this.level = 1;
                    break;
                case "INFO":
                    this.level = 2;
                    break;
                case "WARN":
                    this.level = 3;
                    break;
                case "ERROR":
                    this.level = 4;
                    break;
                case "FATAL":
                    this.level = 5;
                    break;
                case "TRACE":
                default:
                    this.level = 0;
            }
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isTraceEnabled() {
        return level <= 0;
    }

    @Override
    public void trace(String s) {
        if (!isTraceEnabled()) {
            return;
        }
        System.out.printf("[%5s] [%18s]: %s\n", "TRACE", name, s);
    }

    @Override
    public void trace(String s, Object o) {
        if (!isTraceEnabled()) {
            return;
        }
        System.out.printf("[%5s] [%18s]: %s\n", "TRACE", name, StringUtil.format(s, o));
    }

    @Override
    public void trace(String s, Object o, Object o1) {
        if (!isTraceEnabled()) {
            return;
        }
        System.out.printf("[%5s] [%18s]: %s\n", "TRACE", name, StringUtil.format(s, o, o1));
    }

    @Override
    public void trace(String s, Object... objects) {
        if (!isTraceEnabled()) {
            return;
        }
        System.out.printf("[%5s] [%18s]: %s\n", "TRACE", name, StringUtil.format(s, objects));
    }

    @Override
    public void trace(String s, Throwable throwable) {
    }

    @Override
    public boolean isTraceEnabled(Marker marker) {
        return false;
    }

    @Override
    public void trace(Marker marker, String s) {
    }

    @Override
    public void trace(Marker marker, String s, Object o) {
    }

    @Override
    public void trace(Marker marker, String s, Object o, Object o1) {
    }

    @Override
    public void trace(Marker marker, String s, Object... objects) {
    }

    @Override
    public void trace(Marker marker, String s, Throwable throwable) {
    }

    @Override
    public boolean isDebugEnabled() {
        return level <= 1;
    }

    @Override
    public void debug(String message) {
        if (!isDebugEnabled()) {
            return;
        }
        System.out.printf("[%5s] [%18s]: %s\n", "DEBUG", name, message);
    }

    @Override
    public void debug(String s, Object o) {
        if (!isDebugEnabled()) {
            return;
        }
        System.out.printf("[%5s] [%18s]: %s\n", "DEBUG", name, StringUtil.format(s, o));
    }

    @Override
    public void debug(String s, Object o, Object o1) {
        if (!isDebugEnabled()) {
            return;
        }
        System.out.printf("[%5s] [%18s]: %s\n", "DEBUG", name, StringUtil.format(s, o, o1));
    }

    @Override
    public void debug(String s, Object... objects) {
        if (!isDebugEnabled()) {
            return;
        }
        System.out.printf("[%5s] [%18s]: %s\n", "DEBUG", name, StringUtil.format(s, objects));
    }

    @Override
    public void debug(String s, Throwable throwable) {

    }

    @Override
    public boolean isDebugEnabled(Marker marker) {
        return false;
    }

    @Override
    public void debug(Marker marker, String s) {
    }

    @Override
    public void debug(Marker marker, String s, Object o) {
    }

    @Override
    public void debug(Marker marker, String s, Object o, Object o1) {
    }

    @Override
    public void debug(Marker marker, String s, Object... objects) {
    }

    @Override
    public void debug(Marker marker, String s, Throwable throwable) {

    }

    @Override
    public boolean isInfoEnabled() {
        return level <= 2;
    }

    @Override
    public void info(String message) {
        if (!isInfoEnabled()) {
            return;
        }
        System.out.printf("[%5s] [%18s]: %s\n", "INFO", name, message);
    }

    @Override
    public void info(String s, Object o) {
        if (!isInfoEnabled()) {
            return;
        }
        System.out.printf("[%5s] [%18s]: %s\n", "INFO", name, StringUtil.format(s, o));
    }

    @Override
    public void info(String s, Object o, Object o1) {
        if (!isInfoEnabled()) {
            return;
        }
        System.out.printf("[%5s] [%18s]: %s\n", "INFO", name, StringUtil.format(s, o1));
    }

    @Override
    public void info(String s, Object... objects) {
        if (!isInfoEnabled()) {
            return;
        }
        System.out.printf("[%5s] [%18s]: %s\n", "INFO", name, StringUtil.format(s, objects));
    }

    @Override
    public void info(String s, Throwable throwable) {

    }

    @Override
    public boolean isInfoEnabled(Marker marker) {
        return false;
    }

    @Override
    public void info(Marker marker, String s) {

    }

    @Override
    public void info(Marker marker, String s, Object o) {

    }

    @Override
    public void info(Marker marker, String s, Object o, Object o1) {

    }

    @Override
    public void info(Marker marker, String s, Object... objects) {

    }

    @Override
    public void info(Marker marker, String s, Throwable throwable) {

    }

    @Override
    public boolean isWarnEnabled() {
        return level <= 3;
    }

    @Override
    public void warn(String message) {
        if (!isWarnEnabled()) {
            return;
        }
        System.out.printf("[%5s] [%18s]: %s\n", "WARN", name, message);
    }

    @Override
    public void warn(String s, Object o) {
        if (!isWarnEnabled()) {
            return;
        }
        System.out.printf("[%5s] [%18s]: %s\n", "WARN", name, StringUtil.format(s, o));
    }

    @Override
    public void warn(String s, Object... objects) {
        if (!isWarnEnabled()) {
            return;
        }
        System.out.printf("[%5s] [%18s]: %s\n", "WARN", name, StringUtil.format(s, objects));
    }

    @Override
    public void warn(String s, Object o, Object o1) {
        if (!isWarnEnabled()) {
            return;
        }
        System.out.printf("[%5s] [%18s]: %s\n", "WARN", name, StringUtil.format(s, o, o1));
    }

    @Override
    public void warn(String s, Throwable throwable) {
    }

    @Override
    public boolean isWarnEnabled(Marker marker) {
        return false;
    }

    @Override
    public void warn(Marker marker, String s) {

    }

    @Override
    public void warn(Marker marker, String s, Object o) {

    }

    @Override
    public void warn(Marker marker, String s, Object o, Object o1) {

    }

    @Override
    public void warn(Marker marker, String s, Object... objects) {

    }

    @Override
    public void warn(Marker marker, String s, Throwable throwable) {

    }

    @Override
    public boolean isErrorEnabled() {
        return level <= 4;
    }

    @Override
    public void error(String message) {
        if (!isErrorEnabled()) {
            return;
        }
        System.out.printf("[%5s] [%18s]: %s\n", "ERROR", name, message);
    }

    @Override
    public void error(String s, Object o) {
        if (!isErrorEnabled()) {
            return;
        }
        System.out.printf("[%5s] [%18s]: %s\n", "ERROR", name, StringUtil.format(s, o));
    }

    @Override
    public void error(String s, Object o, Object o1) {
        if (!isErrorEnabled()) {
            return;
        }
        System.out.printf("[%5s] [%18s]: %s\n", "ERROR", name, StringUtil.format(s, o, o1));
    }

    @Override
    public void error(String s, Object... objects) {
        if (!isErrorEnabled()) {
            return;
        }
        System.out.printf("[%5s] [%18s]: %s\n", "ERROR", name, StringUtil.format(s, objects));
    }

    @Override
    public void error(String s, Throwable throwable) {

    }

    @Override
    public boolean isErrorEnabled(Marker marker) {
        return false;
    }

    @Override
    public void error(Marker marker, String s) {

    }

    @Override
    public void error(Marker marker, String s, Object o) {

    }

    @Override
    public void error(Marker marker, String s, Object o, Object o1) {

    }

    @Override
    public void error(Marker marker, String s, Object... objects) {

    }

    @Override
    public void error(Marker marker, String s, Throwable throwable) {

    }
}
