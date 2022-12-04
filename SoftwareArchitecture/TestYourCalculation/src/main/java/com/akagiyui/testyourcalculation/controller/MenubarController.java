package com.akagiyui.testyourcalculation.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;

public class MenubarController {

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
}
