package experiment.block;

import java.awt.*;
public class Block {
    public Point[] location;   //存放每个砖块的坐标
    public Point size;         //砖块的尺寸
    public int num;            //砖块的数量
    public boolean[] exist;    //标示砖块是否被敲击

    public Block(GamePanel gp) {
        num = 20;
        //存放砖块的分布
        Point allocation = new Point(4, 5);  //砖块分布4行*5列
        size = new Point(50, 20);      //砖块尺寸为50*20
        location = new Point[num];     //初始化location数组
        for (int i = 0; i < allocation.y; i++) {
            for (int j = 0; j < allocation.x; j++) {
                //初始化各砖块的坐标值，让各砖块均匀分布在屏幕上部区域
                location[i * allocation.x + j] = new Point((gp.width / allocation.x - size.x) / 2 + j * (gp.width / allocation.x), (i * 2 + 1) * size.y);
                System.out.println(location[i * allocation.x + j]);
            }
        }
        exist = new boolean[num];     //初始化exist数组
        for (int i = 0; i < num; i++) {
            exist[i] = true;
        }
    }

    public void update() {
    }

    public void draw(Graphics g) {
        g.setColor(Color.gray);
        for (int i = 0; i < num; i++) {
            if (exist[i] == true) {
                g.fillRect(location[i].x, location[i].y, size.x, size.y);
            }
        }
    }
}
