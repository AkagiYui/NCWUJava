package com.akagiyui.testyourcalculation.scene;

import animatefx.animation.Flash;
import com.akagiyui.testyourcalculation.equation.Equation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextAlignment;
import lombok.Getter;

/**
 * 算式布局类
 */
public class EquationLayout extends HBox {
    /**
     * 题目标签
     */
    private final Label questionLabel;
    /**
     * 答案文本框
     */
    private final TextField answerTextField;
    /**
     * 检查结果标签
     */
    private final Label resultLabel;
    /**
     * 算式
     */
    @Getter
    private Equation equation;

    /**
     * 算式布局
     */
    public EquationLayout() {
        super();
        setSpacing(5); // 设置间距
        setAlignment(Pos.CENTER); // 设置对齐方式
        setPrefHeight(45); // 设置高度

        questionLabel = new Label();
        questionLabel.setPrefWidth(100); // 设置宽度
        questionLabel.setTextAlignment(TextAlignment.RIGHT); // 设置文本对齐方式
        questionLabel.setAlignment(Pos.CENTER_RIGHT); // 设置对齐方式
        questionLabel.setStyle("-fx-font-size: 15px;"); // 设置文字大小

        answerTextField = new TextField();
        answerTextField.setPrefWidth(70); // 设置宽度
        answerTextField.setAlignment(Pos.CENTER); // 设置对齐方式
        answerTextField.setStyle("-fx-font-size: 15px;"); // 设置文字大小

        resultLabel = new Label("请输入答案");
        resultLabel.setPrefWidth(80); // 设置宽度
        resultLabel.setAlignment(Pos.CENTER_LEFT); // 设置对齐方式
        resultLabel.setStyle("-fx-font-size: 15px;"); // 设置文字大小

        getChildren().addAll(questionLabel, answerTextField, resultLabel); // 添加组件
    }

    /**
     * 算式布局
     * @param equation 算式
     */
    public EquationLayout(Equation equation) {
        this();
        setEquation(equation);
    }

    /**
     * 设置算式
     * @param equation 算式
     */
    public void setEquation(Equation equation) {
        this.equation = equation;
        questionLabel.setText(equation.toString());
        answerTextField.setText("");
        resultLabel.setText("请输入答案");
    }

    /**
     * 检查答案
     * @return 答案是否正确
     */
    public boolean checkAnswer() {
        var answer = answerTextField.getText();
        if (answer.isBlank()) {
            resultLabel.setText("请输入答案");
            resultLabel.getStyleClass().clear();
        } else {
            try {
                var answerInt = Integer.parseInt(answer);
                if (equation.calculate() == answerInt) {
                    resultLabel.setText("回答正确");
                    resultLabel.getStyleClass().setAll("text-success");
                    return true;
                } else {
                    resultLabel.setText("回答错误");
                    new Flash(answerTextField).setSpeed(2).play();
                    resultLabel.getStyleClass().setAll("text-danger");
                }
            } catch (NumberFormatException e) {
                resultLabel.setText("请输入整数");
                resultLabel.getStyleClass().clear();
            }
        }
        return false;
    }
}
