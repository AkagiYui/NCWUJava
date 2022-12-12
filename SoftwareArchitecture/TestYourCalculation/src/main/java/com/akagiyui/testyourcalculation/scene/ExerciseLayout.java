package com.akagiyui.testyourcalculation.scene;

import com.akagiyui.testyourcalculation.Exercise;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import lombok.Getter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ExerciseLayout extends VBox {
    private List<EquationLayout> equationLayouts;
    private int equationCount;
    private Exercise exercise;
    @Getter
    private boolean saved;

    public ExerciseLayout() {
        super();
        equationLayouts = new ArrayList<>();
        equationCount = 10;
        exercise = new Exercise(200);
        saved = false;
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
        saved = false;
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

    public void saveExercise(File file) {
        if (exercise.save(file)) {
            saved = true;
        }
    }

    public int size() {
        return equationLayouts.size();
    }

}
