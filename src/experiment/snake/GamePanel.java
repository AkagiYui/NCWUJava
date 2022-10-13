package experiment.snake;

import java.awt.*;
import java.awt.event.*;
public class GamePanel extends Panel implements Runnable, KeyListener{
    public int width;
    public int heigth;
    private Image im;
    private Graphics dbg;
    private Thread gamethread;
    private static final int FPS =30;
    private boolean running = false;
    private boolean isPaused = false;
    private int direction;
    public static final int SOUTH = 0;
    public static final int NORTH = 1;
    public static final int EAST = 2;
    public static final int WEST = 3;
    private Snake sk;  //建立贪吃蛇对象
    private Food bk;   //建立食物对象

    public GamePanel() {

        width = 300;
        heigth = 300;
        setPreferredSize(new Dimension(width, heigth));

        sk = new Snake(this);//实例化贪吃蛇对象，并传递一个GamePanel对象的引用
        bk = new Food(this, sk);//实例化食物对象，并传递一个GamePanel对象和Snake对象的引用

        setFocusable(true);
        requestFocus();
        addKeyListener(this);
    }

    public int getDirection() {
        return direction;
    }

    public void gameStart() {
        if (!running) {
            gamethread = new Thread(this);
            gamethread.start();
        }
    }

    public void gameStop() {
        running = false;
    }

    public void gamePaint() {
        Graphics g;
        try {
            g = this.getGraphics();
            if (g != null && im != null) {
                g.drawImage(im, 0, 0, null);
            }
            g.dispose();
        } catch (Exception e) {
        }
    }

    public void gameRender() {
        if (im == null) {
            im = createImage(width, heigth);
            if (im == null) {
                System.out.println("im is null");
            } else {
                dbg = im.getGraphics();
            }
        }

        dbg.setColor(Color.white);
        dbg.fillRect(0, 0, width, heigth);
        sk.draw(dbg);//在后备缓冲区绘制贪吃蛇的图形
        bk.draw(dbg);//在后备缓冲区绘制食物的图形
    }

    public void gameUpdate() {

        if (!isPaused) {
            sk.update(); //更新贪吃蛇的坐标位置
            bk.update();//更新食物的坐标位置

        }
    }

    public void run() {
        long t1,t2,dt,sleepTime;
        long period=1000/FPS;  //计算每一次循环需要的执行时间，单位毫秒
        t1=System.nanoTime();  //保存游戏循环执行前的系统时间，单位纳秒

        while(true){
            gameUpdate();
            gameRender();
            gamePaint();
            t2= System.nanoTime() ; //游戏循环执行后的系统时间，单位纳秒
            dt=(t2-t1)/1000000L;  //本次循环实际花费的时间，并转换为毫秒
            sleepTime = period - dt;//计算本次循环剩余的时间，单位毫秒
            if(sleepTime<=0)        //防止sleepTime值为负数
                sleepTime=2;
            try {
                Thread.sleep(sleepTime); //让线程休眠，由sleepTime值决定
            } catch (InterruptedException ex) { }
            t1 = System.nanoTime();  //重新获取当前系统时间
        }
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        int keycode = e.getKeyCode();

        if (keycode == KeyEvent.VK_P) {
            isPaused = !isPaused;
            System.out.println("key is P");
        }

        if (!isPaused ) {
            switch (keycode) {

                case KeyEvent.VK_DOWN:
                    direction = SOUTH;
                    System.out.println("key is down" + direction);
                    break;
                case KeyEvent.VK_UP:
                    direction = NORTH;
                    System.out.println("key is up" + direction);
                    break;
                case KeyEvent.VK_RIGHT:
                    direction = EAST;
                    System.out.println("key is right" + direction);
                    break;
                case KeyEvent.VK_LEFT:
                    direction = WEST;
                    System.out.println("key is left" + direction);
                    break;
            }
        }
    }

    public void keyReleased(KeyEvent e) {
    }
}
