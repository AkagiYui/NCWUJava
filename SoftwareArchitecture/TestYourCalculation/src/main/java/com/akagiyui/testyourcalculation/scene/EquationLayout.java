package com.akagiyui.testyourcalculation.scene;

import animatefx.animation.Flash;
import com.akagiyui.testyourcalculation.equation.Equation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextAlignment;
import lombok.Getter;

public class EquationLayout extends HBox {
    private final Label questionLabel;
    private final TextField answerTextField;
    private final Label resultLabel;
    @Getter
    private Equation equation;

    public EquationLayout() {
        super();
        setSpacing(5);
        setAlignment(Pos.CENTER);
        setPrefHeight(40);

        questionLabel = new Label();
        questionLabel.setPrefWidth(80);
        questionLabel.setTextAlignment(TextAlignment.RIGHT);
        questionLabel.setAlignment(Pos.CENTER_RIGHT);
        questionLabel.getStyleClass().add("lbl, lbl-default");

        answerTextField = new TextField();
        answerTextField.setPrefWidth(50);

        resultLabel = new Label("请输入答案");
        resultLabel.setPrefWidth(80);
        getChildren().addAll(questionLabel, answerTextField, resultLabel);
    }

    public void setEquation(Equation equation) {
        this.equation = equation;
        questionLabel.setText(equation.toString());
        answerTextField.setText("");
        resultLabel.setText("");
    }

    public boolean checkAnswer() {
        var answer = answerTextField.getText();
        if (answer.isBlank()) {
            resultLabel.setText("请输入答案");
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
        }
        return false;
    }
}
