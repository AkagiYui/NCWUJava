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
    private final Label questionLabel; // 题目标签
    private final TextField answerTextField; // 答案文本框
    private final Label resultLabel; // 结果标签
    @Getter
    private Equation equation; // 算式

    public EquationLayout() {
        super();
        setSpacing(5); // 设置间距
        setAlignment(Pos.CENTER); // 设置对齐方式
        setPrefHeight(40); // 设置高度

        questionLabel = new Label();
        questionLabel.setPrefWidth(100);
        questionLabel.setTextAlignment(TextAlignment.RIGHT);
        questionLabel.setAlignment(Pos.CENTER_RIGHT);
        questionLabel.setStyle("-fx-font-size: 15px;");

        answerTextField = new TextField();
        answerTextField.setPrefWidth(60);
        answerTextField.setAlignment(Pos.CENTER);

        resultLabel = new Label("请输入答案");
        resultLabel.setPrefWidth(80);
        resultLabel.setStyle("-fx-font-size: 15px;");

        getChildren().addAll(questionLabel, answerTextField, resultLabel);
    }

    public void setEquation(Equation equation) {
        this.equation = equation;
        questionLabel.setText(equation.toString());
        answerTextField.setText("");
        resultLabel.setText("请输入答案");

//        new FadeIn(questionLabel).setSpeed(1).play();
//        new FadeIn(answerTextField).setSpeed(1).play();
    }

    public boolean checkAnswer() {
        var answer = answerTextField.getText();
        if (answer.isBlank()) {
            resultLabel.setText("请输入答案");
            resultLabel.getStyleClass().clear();
            return false;
        }
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
        return false;
    }
}
