package experiment.snake;

import java.awt.*;
import java.awt.event.*;

/**
 * 游戏主窗口
 */
public class GameFrame extends Frame {
    public GameFrame() {
        super("贪吃蛇 By AkagiYui"); //创建一个窗口对象
        this.addWindowListener(new WindowAdapter() { //添加窗口事件监听器
            @Override
            public void windowClosing(WindowEvent e) { //窗口关闭事件
                System.exit(0); //退出程序
            }
        });

        var panel = new GamePanel(); //创建游戏面板
        //设置窗口位置在屏幕中间
        var screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(
                (screenSize.width - panel.width) / 2,
                (screenSize.height - panel.height) / 2
        );
        this.add(panel, BorderLayout.CENTER); //添加游戏面板到窗口
        this.pack(); //调整窗口大小
        this.setResizable(false); //禁止调整窗口大小
        this.setVisible(true); //显示窗口
        panel.gameStart(); //启动游戏
    }
}
