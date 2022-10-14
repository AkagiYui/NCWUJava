package experiment.snake;

import java.awt.*;

public class Snake {
    GamePanel gamePanel;

    public static final int DOWN = 0; //向下
    public static final int UP = 1; //向上
    public static final int RIGHT = 2; //向右
    public static final int LEFT = 3; //向左
    public static final int MAXLENGTH = 20000; //蛇身最大长度
    private final Point[] body = new Point[MAXLENGTH]; //点类型数组，保存蛇身各小球坐标
    private int headIndex = -1; //指示蛇头位置
    private int tailIndex = -1; //指示蛇尾位置
    public int length = 1; //蛇身长度
    public Point head = new Point(50, 50); //蛇头小球的坐标
    public int diameter = 10; //蛇身小球的半径
    private int speed = 3; //蛇的速度

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        if (this.direction == direction) return;
        this.direction = direction;
        System.out.println("direction = " + direction);
    }

    private int direction = 0; //蛇的移动方向

    public Snake(GamePanel gp) {
        gamePanel = gp; //通过构造方法的参数来获取GamePanel对象的引用
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    /**
     * 更新蛇身位置
     */
    public void update() {
        // 运行速度
        switch (direction) { // 按不同方向值分别改变蛇头小球的坐标
            case DOWN -> head.y += speed;
            case UP -> head.y -= speed;
            case RIGHT -> head.x += speed;
            case LEFT -> head.x -= speed;
        }

        if (head.x > gamePanel.width) { //蛇头小球超出右边界
            head.x = -diameter; //蛇头小球的横坐标设置为-半径
        }
        if (head.y > gamePanel.height) { //蛇头小球超出下边界
            head.y = -diameter; //蛇头小球的纵坐标设置为-半径
        }
        if (head.x < -diameter) { //蛇头小球超出左边界
            head.x = gamePanel.width; //蛇头小球的横坐标设置为GamePanel的宽度
        }
        if (head.y < -diameter) { //蛇头小球超出上边界
            head.y = gamePanel.height; //蛇头小球的纵坐标设置为GamePanel的高度
        }

        headIndex = (headIndex + 1) % body.length; // 更新蛇头指针的位置
        tailIndex = (headIndex + body.length - length + 1) % body.length; // 更新蛇尾指针位置
        body[headIndex] = new Point(head.x, head.y); // 保存蛇头小球的坐标
    }

    public void lengthen() {
        // 蛇身伸长一个单位
        if (length < Snake.MAXLENGTH) {
            length++; //若蛇长未达到最大值，蛇身伸长一个单位
        }
    }

    /**
     * 绘制贪吃蛇图形
     * @param graphics Graphics对象 用于绘制贪吃蛇
     */
    public void draw(Graphics graphics) {
        var oldColor = graphics.getColor(); //保存原来的颜色
        // 绘制蛇身
        if (length > 1) {
            graphics.setColor(Color.blue); //设置蛇身为蓝色
            var i = tailIndex;
            while (i != headIndex) {
                if (i == tailIndex) {
                    graphics.fillOval(body[i].x, body[i].y, diameter, diameter);
                } else {
                    graphics.fillRect(body[i].x, body[i].y, diameter, diameter);
                }
                i = (i + 1) % body.length;
            }
        }

        //绘制蛇头
        graphics.setColor(Color.red);
        graphics.fillOval(body[headIndex].x, body[headIndex].y, diameter, diameter);

        graphics.setColor(oldColor); //恢复原来的颜色
    }
}
