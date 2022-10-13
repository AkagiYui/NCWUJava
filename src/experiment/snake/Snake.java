package experiment.snake;

import java.awt.*;
public class Snake {

    GamePanel gameP;
    private Point[] body;//点类型数组，保存蛇身各小球坐标
    public static final int MAXLENTH = 20;//蛇身最大长度
    private int head;//指示蛇头位置
    private int tail;
    public int length;//蛇身长度
    private int speed;//运行速度
    public int x;//蛇头小球的横坐标
    public int y;
    public int diameter;//蛇身小球的半径
    //对各变量进行初始化
    public Snake(GamePanel gp) {
        gameP = gp;//通过构造方法的参数来获取GamePanel对象的引用
        body = new Point[MAXLENTH];
        head = -1;
        tail = -1;
        length = 1;
        speed = 10;
        x = 50;
        y = 50;
        diameter = 10;
    }
    //更新贪吃蛇坐标
    public void update() {


        int direction=gameP.getDirection();//获取玩家的按键信息
        switch (direction) { //按不同方向值分别改变蛇头小球的坐标
            case GamePanel.SOUTH:
                y += speed;
                break;
            case GamePanel.NORTH:
                y -= speed;
                break;
            case GamePanel.EAST:
                x += speed;
                break;
            case GamePanel.WEST:
                x -= speed;
                break;
        }

        if (x > gameP.width) {
            x = -diameter;
        }
        if (y > gameP.heigth) {
            y = -diameter;
        }
        if (x < -diameter) {
            x = gameP.width;
        }
        if (y < -diameter) {
            y = gameP.heigth;
        }

        head = (head + 1) % body.length;//更新蛇头指针的位置

        tail = (head + body.length - length + 1) % body.length;//更新蛇尾指针位置
        body[head] = new Point(x, y);//保存蛇头小球的坐标
    }
    //绘制贪吃蛇图形
    public void draw(Graphics g) {
        g.setColor(Color.blue);//设置蛇身为蓝色
        if (length > 1) {
            int i = tail;
            while (i != head) {//循环绘制蛇身各个小球
                g.fillOval(body[i].x, body[i].y, diameter, diameter);
                i = (i + 1) % body.length;
            }
        }

        g.setColor(Color.red);
        g.fillOval(body[head].x, body[head].y, diameter, diameter);
    }
}
