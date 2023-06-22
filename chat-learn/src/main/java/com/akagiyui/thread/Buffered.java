package com.akagiyui.thread;

/**
 * 缓冲区  生产的、消费的产品和行为
 *
 * @author AkagiYui
 */
public class Buffered {
    int index = 0;//生产的索引
    Product[] pr = new Product[10];

    //生产者方法 往缓冲区放产品
    public synchronized void push(String name, int number) {
        try {
            if (index == pr.length) {
                System.out.println("---生产者进入等待");
                this.wait();
                System.out.println("---生产者等待结束");
            }
            this.notifyAll();
            pr[index] = new Product(name, number);
            index++;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /** 消费者方法 */
    public synchronized Product pop() {
        try {
            if (index == 0) {
                System.out.println("===消费者进入等待");
                this.wait();//等待阻塞 锁对象
                System.out.println("===消费者等待结束");
            }
            index--;
            this.notifyAll();//通过所有wait又等待阻塞转就绪 释放锁对象
            return pr[index];
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
