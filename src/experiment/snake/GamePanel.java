package experiment.snake;

import java.awt.*;
import java.awt.event.*;

/**
 * 游戏界面
 */
public class GamePanel extends Panel implements Runnable, KeyListener{
    public int width = 300; //面板宽度
    public int height = 300; //面板高度
    private Image image; //游戏画面
    private Graphics graphics; //游戏画笔
    private static final int TICK = 60; //游戏刷新频率
    private boolean isPaused = false; //游戏是否暂停
    private int direction; //当前方向
    public static final int SOUTH = 0; //向下
    public static final int NORTH = 1; //向上
    public static final int EAST = 2; //向右
    public static final int WEST = 3; //向左
    private final Snake snake; //建立贪吃蛇对象
    private final Food food; //建立食物对象

    public GamePanel() {
        setPreferredSize(new Dimension(width, height)); //设置面板偏好大小
        snake = new Snake(this); //实例化贪吃蛇对象，并传递一个GamePanel对象的引用
        food = new Food(this, snake); //实例化食物对象，并传递一个GamePanel对象和Snake对象的引用
        setFocusable(true); //设置面板可获得焦点
        requestFocus(); //请求获得焦点
        addKeyListener(this); //添加键盘监听器
    }

    /**
     * 获取当前方向
     * @return 当前方向
     */
    public int getDirection() {
        return direction;
    }

    /**
     * 开始游戏
     */
    public void gameStart() {
        new Thread(this).start();
    }

    /**
     * 画面刷新
     */
    public void gamePaint() {
        try {
            var g = this.getGraphics();
            if (g != null && image != null) {
                g.drawImage(image, 0, 0, null);
            }
            assert g != null;
            g.dispose();
        } catch (Exception ignored) {}
    }

    /**
     * 绘制游戏界面
     */
    public void gameRender() {
        if (image == null) {
            image = createImage(width, height);
            graphics = image.getGraphics();
        }
        graphics.setColor(Color.white); //设置画笔颜色
        graphics.fillRect(0, 0, width, height); //填充背景色
        snake.draw(graphics); //在后备缓冲区绘制贪吃蛇的图形
        food.draw(graphics); //在后备缓冲区绘制食物的图形
    }

    /**
     * 游戏数据更新
     */
    public void gameUpdate() {
        if (!isPaused) {
            snake.update(); //更新贪吃蛇的坐标位置
            food.update(); //更新食物的坐标位置
        }
    }

    @Override
    public void run() {
        var period = 1000 / TICK;  //计算每一次循环需要的执行时间，单位毫秒

        //noinspection InfiniteLoopStatement
        while (true) {
            var timeBefore = System.nanoTime();  //获取当前系统时间，单位纳秒
            gameUpdate(); //更新游戏数据
            gameRender(); //绘制游戏界面
            gamePaint(); //刷新游戏画面
            var timeAfter = System.nanoTime(); //获取当前系统时间，单位纳秒
            var timeSpent = timeAfter - timeBefore; //计算游戏循环执行所花费的时间，单位纳秒
            var sleepTime = period - timeSpent / 1000000; //计算需要休眠的时间，单位毫秒
            if (sleepTime < 0) {
                sleepTime = 2; //如果休眠时间小于0，则设置休眠时间为2毫秒
            }
            try {
                //noinspection BusyWait
                Thread.sleep(sleepTime); //休眠
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 键盘按下事件
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        var keycode = e.getKeyCode(); // 获取按键的键值

        // 游戏暂停
        if (keycode == KeyEvent.VK_P) {
            isPaused = !isPaused;
            System.out.println("key is P");
        }

        // 更新方向
        if (!isPaused) {
            switch (keycode) {
                case KeyEvent.VK_DOWN -> {
                    direction = SOUTH;
                    System.out.println("key is down" + direction);
                }
                case KeyEvent.VK_UP -> {
                    direction = NORTH;
                    System.out.println("key is up" + direction);
                }
                case KeyEvent.VK_RIGHT -> {
                    direction = EAST;
                    System.out.println("key is right" + direction);
                }
                case KeyEvent.VK_LEFT -> {
                    direction = WEST;
                    System.out.println("key is left" + direction);
                }
            }
        }
    }

    /**
     * 键盘释放事件
     * @param e the event to be processed
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * 键盘释放事件
     * @param e the event to be processed
     */
    @Override
    public void keyReleased(KeyEvent e) {
    }
}
