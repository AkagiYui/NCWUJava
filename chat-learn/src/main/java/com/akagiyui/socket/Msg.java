package com.akagiyui.socket;

import java.io.Serializable;

/**
 * @author AkagiYui
 */
public class Msg implements Serializable {
    public static final long serialVersionUID = 42L;
    private String name;
    private String msg;

    public Msg() {
    }

    public Msg(String name, String msg) {
        this.name = name;
        this.msg = msg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
