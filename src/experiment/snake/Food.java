package experiment.snake;

import java.awt.*;
import java.util.Random;

public class Food {
    public Point location;//食物的坐标
    public Point size;//食物方块的尺寸
    private GamePanel gameP;//GamePanel对象
    private Snake snk;//Snake对象
    private Random rand;//随机类对象

    public Food(GamePanel gp, Snake sk) {
        gameP = gp;//通过构造方法的参数来获取GamePanel对象的引用
        snk = sk;//通过构造方法的参数来获取Snake对象的引用
        rand = new Random();
        //位置随机出现
        location = new Point(Math.abs(rand.nextInt() % gameP.width),Math.abs(rand.nextInt() % gameP.heigth));
        size = new Point(sk.diameter, sk.diameter);
    }
    public void update() {
        //碰撞检测，判断贪吃蛇是否吃到食物
        if ((snk.x-location.x)*(snk.x-location.x)+
                (snk.y-location.y)*(snk.y-location.y) < snk.diameter*snk.diameter) {
            //若贪吃蛇的蛇头与食物发生碰撞，则随机生成新的食物位置
            location = new Point(Math.abs(rand.nextInt() % gameP.width),  Math.abs(rand.nextInt() % gameP.heigth));
            if (snk.length < Snake.MAXLENTH) {
                snk.length++;//若蛇长未达到最大值，蛇身伸长一个单位
            }
        }
    }
    //绘制食物图形
    public void draw(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(location.x, location.y, size.x, size.y);
    }
}
