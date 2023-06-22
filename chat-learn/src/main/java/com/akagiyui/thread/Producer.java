package com.akagiyui.thread;
/**
 * 消费者
 * @author AkagiYui
 */
public class Producer implements Runnable{
    Buffered bu = null;
    public Producer(Buffered bu) {
        this.bu = bu;
    }
    @Override
    public void run() {
        for (int i=0;i<30;i++){
            bu.push("小米",i);
            System.out.println("生产的编号:"+i);
        }
    }
}
