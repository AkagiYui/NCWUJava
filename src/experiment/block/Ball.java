package experiment.block;

import java.awt.*;
public class Ball {
    private final Point location;   //小球的左上角坐标
    private final int diameter;     //小球的直径
    private int dx;//水平速度
    private int dy;//垂直速度
    private GamePanel gameP;//GamePanel对象
    private Block blk;//砖块对象
    private Pad pd;//挡板对象
    private boolean padCanBounce = false;
    public Ball(GamePanel gp, Pad p, Block bk) {
        gameP = gp;
        blk = bk;
        pd = p;
        diameter = 20;
        location = new Point(pd.location.x + (pd.size.x - diameter) / 2, pd.location.y - pd.size.y);
        dx = 5;
        dy = -5;
    }

    public void update() {
        if (gameP.ballMove) {
            location.x += dx;
            location.y += dy;
            wallBounced();
            blockBounced();
            padBounced();
        } else {
            location.setLocation(pd.location.x + (pd.size.x - diameter) / 2, pd.location.y - pd.size.y);
        }
    }
    public void draw(Graphics g) {
        g.setColor(Color.blue);
        g.fillOval(location.x, location.y, diameter, diameter);
    }

    public boolean Bounce(Point bk_location, Point bk_size) {
        if ((location.x > bk_location.x - diameter) && (location.x < bk_location.x + bk_size.x) && (location.y > bk_location.y - diameter) && (location.y < bk_location.y + bk_size.y)) {
            return true;
        } else {
            return false;
        }
    }

    public void wallBounced() {
        if ((location.x > gameP.width - diameter) || (location.x < 0)) {
            dx = -dx;
            padCanBounce = true;
        }
        if (location.y < 0) {
            dy = -dy;
            padCanBounce = true;
        }
        if (location.y > gameP.heigth) {
            gameP.ballMove = false;
            padCanBounce = true;
        }
    }

    public void blockBounced() {
        Point local1, local2, local3, size1, size2, size3;
        for (int i = 0; i < blk.num; i++) {
            //按照1:8:1的比例将每个砖块划分为三个区域
            if(blk.exist[i]==false) continue;

            local1 = blk.location[i];
            size1 = new Point(blk.size.x * 1 / 10, blk.size.y);
            local2 = new Point(blk.location[i].x + blk.size.x * 1 / 10, blk.location[i].y);
            size2 = new Point(blk.size.x * 8 / 10, blk.size.y);
            local3 = new Point(blk.location[i].x + blk.size.x * 9 / 10, blk.location[i].y);
            size3 = new Point(blk.size.x * 1 / 10, blk.size.y);
            if (Bounce(local2, size2)) {
                dy = -dy;
                blk.exist[i] = false;
                padCanBounce = true;
            } else if (Bounce(local1, size1)) {
                if (dx > 0) {
                    dx = -dx;
                } else {
                    dy = -dy;
                }
                blk.exist[i] = false;
                padCanBounce = true;
            } else if (Bounce(local3, size3)) {
                if (dx < 0) {
                    dx = -dx;
                } else {
                    dy = -dy;
                }
                blk.exist[i] = false;
                padCanBounce = true;
            }
        }
    }

    public void padBounced() {
        Point local1, local2, local3, size1, size2, size3;
        //水平方向按1:5：1的比例分割挡板，垂直方向只取挡板高度的1/4
        local1 = pd.location;
        size1 = new Point(pd.size.x * 1 / 7, pd.size.y / 4);
        local3 = new Point(pd.location.x + pd.size.x * 6 / 7, pd.location.y);
        size3 = new Point(pd.size.x * 1 / 7, pd.size.y / 4);
        local2 = new Point(pd.location.x + pd.size.x * 1 / 7, pd.location.y);
        size2 = new Point(pd.size.x * 5 / 7, pd.size.y / 4);
        if (padCanBounce) {
            if (Bounce(local2, size2)) {
                dy = -dy;
                padCanBounce = false;
            } else if (Bounce(local1, size1)) {
                padCanBounce = false;
                if (dx > 0) {
                    dx = -dx;
                }
                dy = -dy;
            } else if (Bounce(local3, size3)) {
                padCanBounce = false;
                if (dx < 0) {
                    dx = -dx;
                }
                dy = -dy;
            }
        }
    }
}
