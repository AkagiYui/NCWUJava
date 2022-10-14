package experiment.snake;

import java.awt.*;
import java.util.Random;

public class Food {
    public Point location; // 食物的坐标
    public Point size; // 食物方块的尺寸
    private final GamePanel gamePanel; // GamePanel对象
    private final Snake snake; // Snake对象
    private final Random rand = new Random(); // 随机类对象

    public Food(GamePanel gamePanel, Snake snake) {
        this.gamePanel = gamePanel; //通过构造方法的参数来获取GamePanel对象的引用
        this.snake = snake; //通过构造方法的参数来获取Snake对象的引用

        size = new Point(snake.diameter, snake.diameter);
        refreshLocation(); //位置随机出现
    }
    public void update() {
        //碰撞检测，判断贪吃蛇是否吃到食物
        var deltaX = snake.x - location.x;
        var deltaY = snake.y - location.y;
        if (deltaX * deltaX + deltaY * deltaY < snake.diameter * snake.diameter) { //吃到食物
            //若贪吃蛇的蛇头与食物发生碰撞，则随机生成新的食物位置
            snake.length++; //蛇身长度加1
            refreshLocation(); //刷新食物位置
            snake.lengthen();
        }
    }

    private void refreshLocation() {
        // 随机生成食物的位置
        location = new Point(
            Math.abs(rand.nextInt() % (gamePanel.width - snake.diameter)),
            Math.abs(rand.nextInt() % (gamePanel.height - snake.diameter))
        );
    }

    /**
     * 绘制食物
     * @param g: Graphics对象 用于绘制食物
     */
    public void draw(Graphics g) {
        var oldColor = g.getColor();
        g.setColor(Color.black);
        g.fillRect(location.x, location.y, size.x, size.y);
        g.setColor(oldColor);
    }
}
