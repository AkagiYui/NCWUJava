package com.akagiyui.foundation.experiment.fourth;

/*
1、 编写Book类，代表教材。要求如下：
（1）属性：名称（title）、页数（pageNum）
（2）页数不能多于600页，否则输出信息错误，并赋默认值600
（3）为属性提供赋值和取值方法
（4）info方法，控制台输出每本教材的名称和页数
 （5）编写测试类BookTest进行测试，为Book的属性赋初值。
 */
public class BookTest {
    public static void main(String[] args) {
        Book book1 = new Book("Java从入门到精通", 1000);
        Book book2 = new Book("Java从入门到放弃", 100);
        book1.info(); // 教材名称：Java从入门到精通，页数：600
        book2.info();
    }
}

