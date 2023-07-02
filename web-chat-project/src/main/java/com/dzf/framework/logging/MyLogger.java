package com.dzf.framework.logging;

import com.dzf.framework.StringUtil;
import org.slf4j.Logger;
import org.slf4j.Marker;

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

    public MyLogger(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isTraceEnabled() {
        return true;
    }

    @Override
    public void trace(String s) {
        System.out.printf("[%5s] [%18s]: %s\n", "TRACE", name, s);
    }

    @Override
    public void trace(String s, Object o) {
        System.out.printf("[%5s] [%18s]: %s\n", "TRACE", name, StringUtil.format(s, o));
    }

    @Override
    public void trace(String s, Object o, Object o1) {
        System.out.printf("[%5s] [%18s]: %s\n", "TRACE", name, StringUtil.format(s, o, o1));
    }

    @Override
    public void trace(String s, Object... objects) {
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
        info(s);
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
        return true;
    }

    @Override
    public void debug(String message) {
        System.out.printf("[%5s] [%18s]: %s\n", "DEBUG", name, message);
    }

    @Override
    public void debug(String s, Object o) {
        System.out.printf("[%5s] [%18s]: %s\n", "DEBUG", name, StringUtil.format(s, o));
    }

    @Override
    public void debug(String s, Object o, Object o1) {
        System.out.printf("[%5s] [%18s]: %s\n", "DEBUG", name, StringUtil.format(s, o, o1));
    }

    @Override
    public void debug(String s, Object... objects) {
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
        return true;
    }

    @Override
    public void info(String message) {
        System.out.printf("[%5s] [%18s]: %s\n", "INFO", name, message);
    }

    @Override
    public void info(String s, Object o) {
        System.out.printf("[%5s] [%18s]: %s\n", "INFO", name, StringUtil.format(s, o));
    }

    @Override
    public void info(String s, Object o, Object o1) {
        System.out.printf("[%5s] [%18s]: %s\n", "INFO", name, StringUtil.format(s, o1));
    }

    @Override
    public void info(String s, Object... objects) {
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
        return true;
    }

    @Override
    public void warn(String message) {
        System.out.printf("[%5s] [%18s]: %s\n", "WARN", name, message);
    }

    @Override
    public void warn(String s, Object o) {
        System.out.printf("[%5s] [%18s]: %s\n", "WARN", name, StringUtil.format(s, o));
    }

    @Override
    public void warn(String s, Object... objects) {
        System.out.printf("[%5s] [%18s]: %s\n", "WARN", name, StringUtil.format(s, objects));
    }

    @Override
    public void warn(String s, Object o, Object o1) {
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
        return true;
    }

    @Override
    public void error(String message) {
        System.out.printf("[%5s] [%18s]: %s\n", "ERROR", name, message);
    }

    @Override
    public void error(String s, Object o) {
        System.out.printf("[%5s] [%18s]: %s\n", "ERROR", name, StringUtil.format(s, o));
    }

    @Override
    public void error(String s, Object o, Object o1) {
        System.out.printf("[%5s] [%18s]: %s\n", "ERROR", name, StringUtil.format(s, o, o1));
    }

    @Override
    public void error(String s, Object... objects) {
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
