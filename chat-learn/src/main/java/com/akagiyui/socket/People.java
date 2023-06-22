package com.akagiyui.socket;

import java.io.Serializable;

//序列化对象 该类要实现Serializable
public class People implements Serializable {
    public static final long serialVersionUID = 42L;
    private String name;
    private String msg;
    private String account;
    private String fAccount;
    public People() {
    }

    public People(String name, String msg, String account, String fAccount) {
        this.name = name;
        this.msg = msg;
        this.account = account;
        this.fAccount = fAccount;
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
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getfAccount() {
        return fAccount;
    }

    public void setfAccount(String fAccount) {
        this.fAccount = fAccount;
    }
}
