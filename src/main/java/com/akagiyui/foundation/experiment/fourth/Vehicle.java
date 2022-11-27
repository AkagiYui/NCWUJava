package main.java.com.akagiyui.foundation.experiment.fourth;
/*
3、定义一个交通工具类Vehicle。要求如下
（1）属性：速度speed，体积size
（2）方法：移动move(), 设置速度setSpeed（int speed），加速speedUp()，减速speedDown（）
（3）测试类中实例化一个交通工具对象，并通过方法给它初始化speed，size的值并打印出来。调用加速和减速进行测试
 */
public class Vehicle {
    private int speed; // 速度
    private int size; // 体积

    public Vehicle(int speed, int size) {
        this.speed = speed;
        this.size = size;
    }

    public void printSpeed() {
        System.out.println("speed = " + speed);
    }

    public void printSize() {
        System.out.println("size = " + size);
    }

    public void move() {
        System.out.println("移动");
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void speedUp() {
        speed++;
    }

    public void speedDown() {
        speed--;
    }
}
