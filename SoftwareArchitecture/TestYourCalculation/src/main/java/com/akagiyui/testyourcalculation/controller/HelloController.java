package com.akagiyui.testyourcalculation.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class HelloController {
    @FXML public Button bb;
    @FXML private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        bb.getStyleClass().add("btn-danger");
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}