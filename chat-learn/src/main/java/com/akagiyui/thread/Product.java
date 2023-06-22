package com.akagiyui.thread;

/**
 * 生产和消费 产品
 *
 * @author AkagiYui
 */
public class Product {
    private String name;//产品名
    private int number;//产品编号

    public Product() {
    }

    public Product(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
