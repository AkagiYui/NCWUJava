package com.akagiyui.testyourcalculation.scene;

import com.akagiyui.testyourcalculation.Exercise;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import lombok.Getter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 习题布局类
 */
public class ExerciseLayout extends VBox {
    /**
     * 算式布局列表
     */
    private List<EquationLayout> equationLayouts;
    /**
     * 算式数目
     */
    private int equationCount;
    /**
     * 习题
     */
    private Exercise exercise;
    /**
     * 已保存
     */
    @Getter
    private boolean saved;

    /**
     * 习题布局
     */
    public ExerciseLayout() {
        super();
        equationLayouts = new ArrayList<>();
        equationCount = 5; // TODO 可修改
        exercise = new Exercise(100); // TODO 抽象
        saved = false;
        exercise.recreateEquation(equationCount);

        setPadding(new Insets(10, 0, 0, 0));
        setSpacing(8);
    }

    /**
     * 检查算式答案
     * @return 是否全部正确
     */
    public boolean check() {
        AtomicInteger correctCount = new AtomicInteger();
        equationLayouts.forEach(equationLayout -> {
            if (equationLayout.checkAnswer()) {
                correctCount.getAndIncrement();
            }
        });
        return correctCount.get() == equationLayouts.size();
    }

    /**
     * 重新生成算式
     */
    public void refresh() {
        exercise.recreateEquation();
        equationLayouts.clear();
        exercise.forEach(equation -> {
            var equationLayout = new EquationLayout(equation);
            equationLayouts.add(equationLayout);
        });
        getChildren().clear();
        getChildren().addAll(equationLayouts);
        saved = false; // 重置保存状态
//        getScene().getWindow().setHeight(50 + new EquationLayout().getPrefHeight() * equationCount);
    }

    /**
     * 保存习题
     * @param file 欲保存的文件
     */
    public void saveExercise(File file) {
        if (exercise.save(file)) {
            saved = true;
        }
    }

    /**
     * 加载习题
     * @param file 欲加载的文件
     */
    public void loadExercise(File file) {
        if (exercise.load(file)) {
            equationLayouts.clear();
            exercise.forEach(equation -> {
                var equationLayout = new EquationLayout(equation);
                equationLayouts.add(equationLayout);
            });
            getChildren().clear();
            getChildren().addAll(equationLayouts);
            saved = true; // 重置保存状态
//            getScene().getWindow().setHeight(50 + new EquationLayout().getPrefHeight() * equationCount);
        }
    }

    /**
     * 获取算式布局数量
     * @return 算式布局数量
     */
    public int count() {
        return equationLayouts.size();
    }
}
