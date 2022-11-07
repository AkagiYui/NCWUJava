package com.akagiyui.foundation.experiment.snake;

import java.awt.*;
import java.util.Random;

public class Food {
    private final GamePanel gamePanel; // GamePanel对象
    private final Random rand = new Random(); //随机类对象
    private final Snake snake; //Snake对象
    public Point location; //食物的坐标
    public Point size; //食物方块的尺寸

    public Food(GamePanel gamePanel, Snake snake) {
        this.gamePanel = gamePanel; //通过构造方法的参数来获取GamePanel对象的引用
        this.snake = snake; //通过构造方法的参数来获取Snake对象的引用
        size = new Point(snake.diameter, snake.diameter);
        this.refreshLocation(); //位置随机出现
    }

    /**
     * 碰撞检测，判断贪吃蛇是否吃到食物
     */
    public void update() {
        var deltaX = snake.head.x - location.x;
        var deltaY = snake.head.y - location.y;

        //自动调整方向，自动走向食物
        if (gamePanel.isAuto()) {
            var currentDirection = snake.getDirection();
            if (currentDirection == SnakeDirection.UP || currentDirection == SnakeDirection.DOWN) { //当前方向为上或下
                if (Math.abs(deltaY) <= size.y / 2) { //到达与食物同一高度
                    var deltaX1 = Math.abs(deltaX); //蛇头与食物的横坐标差的绝对值
                    var deltaX2 = Math.abs(gamePanel.width - deltaX1); //游戏区域宽度减去蛇头与食物的横坐标差的绝对值
                    var minDeltaX = Math.min(deltaX1, deltaX2); //取最小值
                    var goLeft = (deltaX < 0 && minDeltaX == deltaX2) || (deltaX > 0 && minDeltaX == deltaX1); //判断是否向左
                    if (goLeft){
                        snake.setDirection(SnakeDirection.LEFT);
                    } else {
                        snake.setDirection(SnakeDirection.RIGHT);
                    }
                }
            }
            if (currentDirection == SnakeDirection.LEFT || currentDirection == SnakeDirection.RIGHT) { //当前方向为左或右
                if (Math.abs(deltaX) <= size.x / 2) { //到达与食物同一宽度
                    var deltaY1 = Math.abs(deltaY); //蛇头与食物的纵坐标差的绝对值
                    var deltaY2 = Math.abs(gamePanel.height - deltaY1); //游戏区域高度减去蛇头与食物的纵坐标差的绝对值
                    var minDeltaY = Math.min(deltaY1, deltaY2); //取最小值
                    var goUp = (deltaY < 0 && minDeltaY == deltaY2) || (deltaY > 0 && minDeltaY == deltaY1); //判断是否向上
                    if (goUp){
                        snake.setDirection(SnakeDirection.UP);
                    } else {
                        snake.setDirection(SnakeDirection.DOWN);
                    }
                }
            }
        }

        if (deltaX * deltaX + deltaY * deltaY < snake.diameter * snake.diameter) { //吃到食物
            //若贪吃蛇的蛇头与食物发生碰撞，则随机生成新的食物位置
            this.refreshLocation(); //刷新食物位置
            gamePanel.scorePlus(); //得分加1
        }
    }

    /**
     * 随机生成食物的位置
     */
    private void refreshLocation() {
        location = new Point(
            Math.abs(rand.nextInt() % (gamePanel.width - size.x)), //-半径是为了防止食物出现在边界外
            Math.abs(rand.nextInt() % (gamePanel.height - size.y))
        );

        //自动调整方向，自动走向食物
        if (gamePanel.isAuto()) {
            var currentDirection = snake.getDirection();
            if (currentDirection == SnakeDirection.UP || currentDirection == SnakeDirection.DOWN) { //当前方向为上或下
                var deltaX1 = Math.abs(snake.head.x - location.x); //蛇头与食物的横坐标差的绝对值
                var deltaX2 = Math.abs(gamePanel.width - deltaX1); //游戏区域宽度减去蛇头与食物的横坐标差的绝对值
                var minDeltaX = Math.min(deltaX1, deltaX2); //取最小值
                var goLeft = (snake.head.x - location.x < 0 && minDeltaX == deltaX2) || (snake.head.x - location.x > 0 && minDeltaX == deltaX1); //判断是否向左
                if (goLeft) {
                    snake.setDirection(SnakeDirection.LEFT);
                } else {
                    snake.setDirection(SnakeDirection.RIGHT);
                }
            }
            if (currentDirection == SnakeDirection.LEFT || currentDirection == SnakeDirection.RIGHT) { //当前方向为左或右
                var deltaY1 = Math.abs(snake.head.y - location.y); //蛇头与食物的纵坐标差的绝对值
                var deltaY2 = Math.abs(gamePanel.height - deltaY1); //游戏区域高度减去蛇头与食物的纵坐标差的绝对值
                var minDeltaY = Math.min(deltaY1, deltaY2); //取最小值
                var goUp = (snake.head.y - location.y < 0 && minDeltaY == deltaY2) || (snake.head.y - location.y > 0 && minDeltaY == deltaY1); //判断是否向上
                if (goUp) {
                    snake.setDirection(SnakeDirection.UP);
                } else {
                    snake.setDirection(SnakeDirection.DOWN);
                }
            }
        }
    }

    /**
     * 绘制食物
     * @param g: Graphics对象 用于绘制食物
     */
    public void draw(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(location.x, location.y, size.x, size.y);
    }
}
