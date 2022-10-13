package experiment.block;

import java.awt.*;
import java.awt.event.*;

public class GamePanel extends Panel implements Runnable, KeyListener {
    public int width;
    public int heigth;
    private Image im;
    private Graphics dbg;
    private static final int FPS = 50;
    private Ball myball;
    private Pad mypad;
    private Block myblock;
    public boolean padMoveRight = false; //标示挡板是否右移
    public boolean padMoveLeft = false;  //标示挡板是否左移
    public boolean ballMove = false;    //标示小球是否处于运动状态
    public boolean gameOver = false;
    public boolean reStart = false;
    public GamePanel() {
        width = 400;  // 设置游戏区域的宽度
        heigth = 400;
        setBackground(Color.pink);
        setPreferredSize(new Dimension(width, heigth));
        mypad = new Pad(this);
        myblock = new Block(this);
        myball = new Ball(this, mypad, myblock);
        this.setFocusable(true);
        this.requestFocus();
        this.addKeyListener(this);
    }

    public void gameStart() {
        Thread gamethread = new Thread(this);
        gamethread.start();
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
        myblock.draw(dbg);
        myball.draw(dbg);
        mypad.draw(dbg);
    }

    public void gameUpdate() {
        isGameOver();
        if (reStart) {
            reSetGame();
            reStart = false;
            gameOver = false;
            ballMove = false;
        }
        if (!gameOver) {
            mypad.update();
            myblock.update();
            myball.update();
        }
    }

    public void isGameOver() {
        int countBlock = 0;
        for (int i = 0; i < myblock.num; i++) {
            if (myblock.exist[i] == true) {
                countBlock++;
            }
        }
        if (countBlock == 0) {
            gameOver = true;
        }
    }

    public void run() {
        long t1,t2,dt,sleepTime;
        long period=1000/FPS;
        t1=System.nanoTime();

        while (true) {
            gameUpdate();
            gameRender();
            gamePaint();
            t2= System.nanoTime() ;
            dt=(t2-t1)/1000000L;
            sleepTime = period - dt;
            if(sleepTime<=0)
                sleepTime=2;
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException ex) { }
            t1 = System.nanoTime();
        }
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        int keycode = e.getKeyCode();
        if (keycode == KeyEvent.VK_RIGHT) {
            padMoveRight = true;
        }
        if (keycode == KeyEvent.VK_LEFT) {
            padMoveLeft = true;
        }
        if (keycode == KeyEvent.VK_SPACE) {
            ballMove = true;
        }
        if (keycode == KeyEvent.VK_R) {
            if (gameOver) {
                reStart = true;

            }
        }
    }

    public void keyReleased(KeyEvent e) {
        int keycode = e.getKeyCode();
        if (keycode == KeyEvent.VK_RIGHT) {
            padMoveRight = false;
        }
        if (keycode == KeyEvent.VK_LEFT) {
            padMoveLeft = false;
        }
    }

    private void reSetGame() {
        mypad = new Pad(this);
        myblock = new Block(this);
        myball = new Ball(this, mypad, myblock);
    }
}
