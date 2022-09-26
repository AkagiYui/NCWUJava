package experiment.fourth;
/*
3、定义一个交通工具类Vehicle。要求如下
（1）属性：速度speed，体积size
（2）方法：移动move(), 设置速度setSpeed（int speed），加速speedUp()，减速speedDown（）
（3）测试类中实例化一个交通工具对象，并通过方法给它初始化speed，size的值并打印出来。调用加速和减速进行测试
 */
public class VehicleTest {
    public static void main(String[] args) {
        var v = new Vehicle(100, 1000);
        v.setSpeed(100);
        v.speedUp();
        v.speedDown();
        v.move();
        v.printSpeed();
        v.printSize();
    }
}
