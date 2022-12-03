package com.akagiyui;

import javax.swing.*;
import java.awt.*;

/**
 * 算式面板
 * @author akagiyui
 */
public class JEquation extends JPanel {
    private final JLabel titleLabel; // 题面标签
    private final JTextField answerField; // 答案输入框
    private final JLabel resultLabel; // 结果标签
    private Equation equation; // 算式对象

    public JEquation() {
        super();
        setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0)); // 设置边框
        setLayout(new GridBagLayout()); // 设置布局

        var c = new GridBagConstraints(); // 网格包约束对象
        c.fill = GridBagConstraints.BOTH;
        c.gridheight = 1; // 高1个单元格
        c.weighty = 1; // 高度权重为1
        c.insets = new Insets(5, 10, 0, 10); // 设置组件间距

        titleLabel = new JLabel("");
        titleLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        titleLabel.setFont(new Font("", Font.PLAIN, 16));
        titleLabel.setPreferredSize(new Dimension(0, 30));
        c.gridx = 0;
        c.weightx = 0.3;
        add(titleLabel, c);

        answerField = new JTextField();
        answerField.setHorizontalAlignment(SwingConstants.CENTER);
        answerField.setPreferredSize(new Dimension(0, 30));
        c.gridx = 1;
        c.weightx = 0.5;
        add(answerField, c);

        resultLabel = new JLabel("?");
        resultLabel.setHorizontalAlignment(SwingConstants.LEFT);
        resultLabel.setFont(new Font("", Font.PLAIN, 20));
        resultLabel.setPreferredSize(new Dimension(0, 10));
        c.gridx = 2;
        c.weightx = 0.2;
        add(resultLabel, c);
    }

    /**
     * 设置算式
     * @param equation 算式
     */
    public void setEquation(Equation equation) {
        this.equation = equation;
        titleLabel.setText(equation.toString());
        answerField.setText("");
        resultLabel.setText("?");
        resultLabel.setForeground(Color.BLACK);
    }

    /**
     * 检查答案
     */
    public void checkAnswer() {
        if (equation == null) {
            return;
        }
        int answer;
        try {
            answer = Integer.parseInt(answerField.getText());
        } catch (NumberFormatException e) {
            resultLabel.setText("?");
            resultLabel.setForeground(Color.BLACK);
            return;
        }
        if (answer == equation.calculate()) {
            resultLabel.setText("√");
            resultLabel.setForeground(Color.GREEN);
        } else {
            resultLabel.setText("×");
            resultLabel.setForeground(Color.BLUE);
        }
    }
}
