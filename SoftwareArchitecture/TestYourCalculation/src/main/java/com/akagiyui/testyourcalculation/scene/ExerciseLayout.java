package com.akagiyui.testyourcalculation.scene;

import com.akagiyui.testyourcalculation.Exercise;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextFlow;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ExerciseLayout extends VBox {
    private List<EquationLayout> equationLayouts;
    private int equationCount;
    private Exercise exercise;
    private TextFlow alertBox;

    public ExerciseLayout() {
        super();
        equationCount = 10;
        equationLayouts = new ArrayList<>();
        exercise = new Exercise(200);
        exercise.recreateEquation(equationCount);

        setPadding(new Insets(10, 0, 0, 0));
        setSpacing(8);
    }

    public boolean check() {
        AtomicInteger correctCount = new AtomicInteger();
        equationLayouts.forEach(equationLayout -> {
            if (equationLayout.checkAnswer()) {
                correctCount.getAndIncrement();
            }
        });
        return correctCount.get() == equationLayouts.size();
    }

    public void refresh() {
        equationLayouts.clear();
        exercise.recreateEquation();
        getChildren().clear();
        for (var equation : exercise) {
            var equationLayout = new EquationLayout();
            equationLayout.setEquation(equation);
            equationLayouts.add(equationLayout);
            getChildren().add(equationLayout);
        }
        getScene().getWindow().setHeight(50 + new EquationLayout().getPrefHeight() * equationCount);
    }
}
