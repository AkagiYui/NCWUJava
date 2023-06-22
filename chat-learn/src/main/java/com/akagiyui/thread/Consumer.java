package com.akagiyui.thread;

/**
 * 消费者
 *
 * @author AkagiYui
 */
public class Consumer implements Runnable {
    Buffered bu = null;

    public Consumer(Buffered bu) {
        this.bu = bu;
    }

    @Override
    public void run() {
        for (int i = 0; i < 30; i++) {
            Product p = bu.pop();
            String name = Thread.currentThread().getName();
            System.out.println(name + "消费了" + p.getName() + "编号" + p.getNumber());
        }
    }
}
