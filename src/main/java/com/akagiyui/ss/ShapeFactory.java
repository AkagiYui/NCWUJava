package main.java.com.akagiyui.ss;

/**
 * 图形工厂类
 */
public class ShapeFactory {
    public static Shape createShape(char type) {
        Shape shape = null;
        switch (type) {
            case 'c' -> shape = new Circle();
            case 'r' -> shape = new Rectangle();
            case 'e' -> shape = new Ellipse();
        }
        return shape;
    }
}
