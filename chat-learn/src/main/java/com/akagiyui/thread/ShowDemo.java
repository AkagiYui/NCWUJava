package com.akagiyui.thread;

/**
 * @author AkagiYui
 */
public class ShowDemo {
    public static void main(String[] args) throws Exception {
        // 消费者和生产者共享的资源
        Buffered bu = new Buffered();
        // 实例化生产者
        Producer pt = new Producer(bu);
        new Thread(pt, "华为").start();
        // 实例化消费者
        Consumer ct = new Consumer(bu);
        Thread t = new Thread(ct, "印度");
        t.start();
        int index = 0;
        while (true) {
            Thread.sleep(3000);
            System.out.println(t.getState());
            if (t.getState() == Thread.State.TERMINATED) {
                index++;
                System.out.println(index + "=========");
                if (index == 5) {
                    synchronized (bu) {
                        bu.notify();
                    }
                    break;
                }
            }
        }
        System.out.println("程序结束");
    }
}
