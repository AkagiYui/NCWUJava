package experiment.snake;

import java.awt.*;
public class Snake {
    GamePanel gamePanel;
    public static final int MAXLENGTH = 20; //蛇身最大长度
    private final Point[] body = new Point[MAXLENGTH]; //点类型数组，保存蛇身各小球坐标
    private int head = -1; //指示蛇头位置
    private int tail = -1; //指示蛇尾位置
    public int length = 1; //蛇身长度
    public int x = 50; //蛇头小球的横坐标
    public int y = 50; //蛇头小球的纵坐标
    public int diameter = 10; //蛇身小球的半径

    public Snake(GamePanel gp) {
        gamePanel = gp; //通过构造方法的参数来获取GamePanel对象的引用
    }

    /**
     * 更新蛇身位置
     */
    public void update() {
        var direction= gamePanel.getDirection(); //获取方向
        // 运行速度
        var speed = 3;
        switch (direction) { // 按不同方向值分别改变蛇头小球的坐标
            case GamePanel.SOUTH -> y += speed;
            case GamePanel.NORTH -> y -= speed;
            case GamePanel.EAST -> x += speed;
            case GamePanel.WEST -> x -= speed;
        }

        if (x > gamePanel.width) { //蛇头小球超出右边界
            x = -diameter; //蛇头小球的横坐标设置为-半径
        }
        if (y > gamePanel.height) { //蛇头小球超出下边界
            y = -diameter; //蛇头小球的纵坐标设置为-半径
        }
        if (x < -diameter) { //蛇头小球超出左边界
            x = gamePanel.width; //蛇头小球的横坐标设置为GamePanel的宽度
        }
        if (y < -diameter) { //蛇头小球超出上边界
            y = gamePanel.height; //蛇头小球的纵坐标设置为GamePanel的高度
        }

        head = (head + 1) % body.length; // 更新蛇头指针的位置

        tail = (head + body.length - length + 1) % body.length; // 更新蛇尾指针位置
        body[head] = new Point(x, y); // 保存蛇头小球的坐标
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
            var i = tail;
            while (i != head) {
                graphics.fillOval(body[i].x, body[i].y, diameter, diameter);
                i = (i + 1) % body.length;
            }
        }

        //绘制蛇头
        graphics.setColor(Color.red);
        graphics.fillOval(body[head].x, body[head].y, diameter, diameter);

        graphics.setColor(oldColor); //恢复原来的颜色
    }
}
