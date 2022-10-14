package experiment.snake;

import java.awt.*;
import java.awt.event.*;

/**
 * 游戏界面
 */
public class GamePanel extends Panel implements Runnable, KeyListener { //继承Panel类，实现Runnable接口和KeyListener接口
    private static final int TICK = 45; //游戏刷新频率
    private final Snake snake; //建立贪吃蛇对象
    private final Food food; //建立食物对象
    private boolean isPaused = false; //游戏是否暂停
    private boolean isRunning = false; //游戏是否正在运行
    public int width = 300; //面板宽度
    public int height = 300; //面板高度
    private int score = 0; //得分
    private Image image; //游戏画面
    private Image oldImage; //上一次游戏画面
    private boolean oldPaused = false; //上一次游戏是否暂停
    private Graphics graphics; //游戏画笔

    public GamePanel() {
        setPreferredSize(new Dimension(width, height)); //设置面板偏好大小
        snake = new Snake(this); //实例化贪吃蛇对象，并传递一个GamePanel对象的引用
        food = new Food(this, snake); //实例化食物对象，并传递一个GamePanel对象和Snake对象的引用
        setFocusable(true); //设置面板可获得焦点
        requestFocus(); //请求获得焦点
        addKeyListener(this); //添加键盘监听器
    }

    /**
     * 开始游戏
     */
    public void gameStart() {
        if (!isRunning) {
            new Thread(this).start(); //启动游戏线程
        }
    }

    /**
     * 画面刷新
     */
    public void gamePaint() {
        try {
            var g = getGraphics(); //获取面板的画笔
            if (g != null && image != null) {
                g.drawImage(image, 0, 0, null); //将游戏画面绘制到面板上
                g.dispose(); //释放画笔
            }
        } catch (Exception ignored) {
        }
    }

    /**
     * 分数增加
     */
    public void scorePlus() {
        score++;
        snake.lengthen(); //蛇身长度加1
    }

    /**
     * 绘制游戏界面
     */
    public void gameRender() {
        graphics.setColor(Color.white); //设置画笔颜色
        graphics.fillRect(0, 0, width, height); //填充背景色
        snake.draw(graphics); //在后备缓冲区绘制贪吃蛇的图形
        food.draw(graphics); //在后备缓冲区绘制食物的图形
        graphics.setColor(Color.black); //设置画笔颜色
        graphics.drawString("得分：" + score, 10, 20); //在后备缓冲区绘制得分
        graphics.drawString("速度：" + snake.getSpeed(), 10, 40); //在后备缓冲区绘制速度
    }

    /**
     * 游戏数据更新
     */
    public void gameUpdate() {
        snake.update(); //更新贪吃蛇的坐标位置
        food.update(); //更新食物的坐标位置
    }

    /**
     * 显示说明画面
     */
    private void showReadme() {
        graphics.setColor(Color.white); //设置画笔颜色
        graphics.fillRect(0, 0, width, height); //填充背景色
        graphics.setColor(Color.BLACK);
        graphics.drawString("按下【空格】键开始游戏", 50, 80);
        graphics.drawString("按【↑】【↓】【←】【→】键控制方向", 50, 100);
        graphics.drawString("按【P】键暂停游戏", 50, 120);
        graphics.drawString("按【-】【=】键改变贪吃蛇速度", 50, 140);
        graphics.drawString("按【空格】键临时增加贪吃蛇速度", 50, 160);
        graphics.drawString("按【I】键查看此消息", 50, 180);
        graphics.drawString("按【A】键切换演示模式", 50, 240);
        gamePaint(); //刷新画面
    }

    /**
     * 游戏线程
     */
    @Override
    public void run() {
        image = createImage(width, height); //创建游戏画面
        oldImage = createImage(width, height); //创建游戏画面
        graphics = image.getGraphics(); //获取游戏画笔
        showReadme(); //显示说明画面

        var period = 1000 / TICK; //计算每一次循环允许的执行时间，单位毫秒
        //noinspection InfiniteLoopStatement
        while (true) {
            var timeBefore = System.nanoTime(); //获取当前系统时间，单位纳秒
            if (isRunning && !isPaused) {
                gameUpdate(); //更新游戏数据
                gameRender(); //绘制游戏界面
                gamePaint(); //刷新游戏画面
            }
            var timeSpent = System.nanoTime() - timeBefore; //计算游戏循环执行所花费的时间，单位纳秒
            var sleepTime = period - timeSpent / 1000000; //计算距离下次计算的时间，单位毫秒
            if (sleepTime < 0) {
                System.out.println("性能不足，要降低游戏刷新率或优化算法了！");
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

    private boolean isSpacePressed = false; //空格键是否按下
    private boolean isIKeyPressed = false; //I键是否按下

    /**
     * 键盘按下事件
     *
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        var keycode = e.getKeyCode(); // 获取按键的键值
        if (!isRunning && keycode == KeyEvent.VK_SPACE) {
            isRunning = true;
            return;
        }

        //游戏暂停
        if (keycode == KeyEvent.VK_P) {
            isPaused = !isPaused; //切换游戏暂停状态
        }
        if (keycode == KeyEvent.VK_I && !isIKeyPressed) {
            isIKeyPressed = true; //I键按下
            oldImage.getGraphics().drawImage(image, 0, 0, null); //复制当前画面
            oldPaused = isPaused; //保存当前游戏暂停状态
            isPaused = true; //暂停游戏
            showReadme(); //显示说明画面
        }

        //更新方向
        if (!isPaused) {
            switch (keycode) {
                case KeyEvent.VK_DOWN -> snake.setDirection(Snake.DOWN); //方向改为向下
                case KeyEvent.VK_UP -> snake.setDirection(Snake.UP); //方向改为向上
                case KeyEvent.VK_LEFT -> snake.setDirection(Snake.LEFT); //方向改为向左
                case KeyEvent.VK_RIGHT -> snake.setDirection(Snake.RIGHT); //方向改为向右
                case KeyEvent.VK_SPACE -> {
                    if (!isSpacePressed) {
                        isSpacePressed = true; //空格键按下
                        snake.setSpeed(snake.getSpeed() * 2); //加速
                    }
                }
            }
        }
    }

    /**
     * 键盘字符被输入事件
     *
     * @param e the event to be processed
     */
    @Override
    public void keyTyped(KeyEvent e) {}

    /**
     * 键盘释放事件
     *
     * @param e the event to be processed
     */
    @Override
    public void keyReleased(KeyEvent e) {
        var keycode = e.getKeyCode(); // 获取按键的键值
        switch (keycode) {
            case KeyEvent.VK_SPACE -> {
                if (isSpacePressed) { //空格释放
                    isSpacePressed = false;
                    snake.setSpeed(snake.getSpeed() / 2); //恢复速度
                }
            }
            case KeyEvent.VK_I -> {
                if (isIKeyPressed) { //I键释放
                    isIKeyPressed = false;
                    graphics.drawImage(oldImage, 0, 0, null); //恢复画面
                    isPaused = oldPaused; //恢复游戏暂停状态
                    gamePaint();
                }
            }
        }
        if (!isPaused) {
            switch (keycode) {
                case KeyEvent.VK_MINUS -> {
                    if (snake.getSpeed() > 1) {
                        snake.setSpeed(snake.getSpeed() - 1); //减速
                    }
                }
                case KeyEvent.VK_EQUALS -> snake.setSpeed(snake.getSpeed() + 1); //加速
            }
        }
    }
}
