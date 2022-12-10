package com.akagiyui.testyourcalculation.scene;

import com.akagiyui.testyourcalculation.CalculateApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.io.IOException;

public class HelloScene extends Scene {
    public HelloScene() throws IOException {
        super(
                new FXMLLoader(
                        CalculateApplication.class.getResource("view/hello-view.fxml")
                ).load(),
                320,
                240
        );
        getStylesheets().add(BootstrapFX.bootstrapFXStylesheet()); // 加载BootstrapFX样式

        var root = getRoot();
        var openButton = (Button)root.lookup("#openButton");
        var createButton = (Button)root.lookup("#createButton");

        openButton.setOnMouseClicked(event -> {
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
        });

        createButton.setOnMouseClicked(event -> {
            var stage = (Stage)getWindow();
            try {
                var s = new DoingScene();
                stage.setScene(s);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}


