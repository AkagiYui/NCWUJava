package com.akagiyui.testyourcalculation.controller;

import com.akagiyui.testyourcalculation.scene.DoingScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    @FXML
    public void openExercise(ActionEvent event) {
        var fileChooser = new FileChooser();
        fileChooser.setTitle("请选择练习簿");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("练习簿", "*.json")
        );

        var source = (Node)event.getSource();
        var stage = source.getScene().getWindow();
        var file = fileChooser.showOpenDialog(stage);
        if (file == null) {
            return;
        }

    }

    @FXML
    public void createExercise(ActionEvent event) throws IOException {
        var source = (Node)event.getSource();
        var stage = (Stage)source.getScene().getWindow();
        stage.setScene(new DoingScene());
    }
}