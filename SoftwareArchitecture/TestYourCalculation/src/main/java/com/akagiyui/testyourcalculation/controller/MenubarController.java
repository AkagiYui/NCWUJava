package com.akagiyui.testyourcalculation.controller;

import com.akagiyui.testyourcalculation.scene.HelloScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.io.IOException;

public class MenubarController {
    @FXML
    public void exitMenuClick(ActionEvent actionEvent) {
        // 二次确认
        var alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("退出");
        alert.setHeaderText("确认退出吗？");
        var result = alert.showAndWait();
        if (result.isPresent() && !result.get().getButtonData().isCancelButton()) {
            System.exit(0);
        }
    }

    @FXML
    public void closeExercise(ActionEvent event) throws IOException {
        // TODO 关闭练习簿
        var source = (MenuItem)event.getSource();
        var scene = source.getParentPopup().getScene();
        var stage = (Stage)scene.getWindow();
        stage.setScene(new HelloScene());
    }
}
