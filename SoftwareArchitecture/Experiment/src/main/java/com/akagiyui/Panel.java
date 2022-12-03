package com.akagiyui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * 主面板
 */
public class Panel extends JPanel implements ActionListener {
    private final int COUNT = 5; // 生成的算式数量
    private final int MAX_OPERAND = 100; // 操作数最大值

    private final List<JEquation> jEquationList; // 算式面板列表
    private final Exercise exercise; // 习题对象

    public Panel() {
        super();
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // 设置边框
        setLayout(new GridBagLayout()); // 设置布局

        var c = new GridBagConstraints(); // 网格包约束对象
        c.fill = GridBagConstraints.BOTH;
        c.gridheight = 1; // 高1个单元格
        c.weighty = 1; // 高度权重为1

        // 顶部文字
        var headerLabel = new JLabel("Test Your Calculation");
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER); // 设置文字居中
        headerLabel.setFont(new Font("", Font.PLAIN, 20)); // 设置字体
        c.gridy = 0; // 第0行
        c.gridx = 0; // 第0列
        c.gridwidth = GridBagConstraints.REMAINDER; // 占据剩余空间
        c.weightx = 1; // 宽度权重为1
        c.ipady = 20; // 高度最小值
        c.ipadx = 100; // 宽度最小值
        c.insets = new Insets(0, 0, 5, 0); // 设置组件间距
        add(headerLabel, c);

        c.insets = new Insets(5, 10, 0, 10);
        c.ipady = 5;
        jEquationList = new ArrayList<>();
        for (int i = 0; i < COUNT; i++) {
            var equation = new JEquation();
            jEquationList.add(equation);
            c.gridy = i + 1;
            add(equation, c);
        }

        // 底部按钮
        c.gridy = COUNT + 1; // 第COUNT+1行
        c.ipady = 10;
        c.ipadx = 0;

        var resetButton = new JButton("Reset");
        resetButton.setPreferredSize(new Dimension(0, 30)); // 设置按钮高度
        resetButton.addActionListener(this);
        c.insets = new Insets(10, 0, 0, 0);
        c.gridx = 0;
        c.gridwidth = 1;
        c.weightx = 0.3;
        add(resetButton, c);

        var checkButton = new JButton("Check");
        checkButton.setPreferredSize(new Dimension(0, 30));
        checkButton.addActionListener(this);
        c.insets = new Insets(10, 10, 0, 0);
        c.gridx = 1;
        c.gridwidth = GridBagConstraints.REMAINDER; // 占据剩余空间
        c.weightx = 0.7;
        add(checkButton, c);

        exercise = new Exercise((short)MAX_OPERAND, "equations.dat"); // 习题对象
        exercise.loadEquations(); // 读取习题
        resetEquations(false);
    }

    /**
     * 按钮事件
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if ("Reset".equals(e.getActionCommand())) {
            resetEquations(true);
        } else if ("Check".equals(e.getActionCommand())) {
            checkEquations();
        }
    }

    /**
     * 检查答案
     */
    private void checkEquations() {
        for (var jEquation : jEquationList) {
            jEquation.checkAnswer();
        }
    }

    /**
     * 重置算式
     * @param recreate 是否重新生成算式
     */
    private void resetEquations(boolean recreate) {
        if (recreate || exercise.getSize() < COUNT) {
            exercise.recreateEquation(COUNT);
        }
        for (var i = 0; i < Math.min(COUNT, jEquationList.size()); i++) {
            jEquationList.get(i).setEquation(exercise.getEquation(i));
        }
    }
}
