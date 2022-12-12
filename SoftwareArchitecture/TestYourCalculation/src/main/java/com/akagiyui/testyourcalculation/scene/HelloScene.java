package com.akagiyui.testyourcalculation.scene;

import com.akagiyui.testyourcalculation.CalculateApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
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

        // 获取组件并设置事件
        var root = getRoot();
        var openButton = (Button)root.lookup("#openButton");
        var createButton = (Button)root.lookup("#createButton");

        // 打开已有练习簿
        openButton.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) { // 左键单击
                // 选择已有练习簿
                var fileChooser = new FileChooser();
                fileChooser.setTitle("请选择练习簿");
                // 设置文件过滤器，只显示json文件
                fileChooser.getExtensionFilters().add(
                        new FileChooser.ExtensionFilter("练习簿", "*.json")
                );
                var stage = (Stage)getWindow();
                var file = fileChooser.showOpenDialog(stage);
                if (file != null) {
                    try {
                        stage.setScene(new DoingScene(file));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        // 创建新的练习簿
        createButton.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                var stage = (Stage)getWindow();
                try {
                    stage.setScene(new DoingScene());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}


