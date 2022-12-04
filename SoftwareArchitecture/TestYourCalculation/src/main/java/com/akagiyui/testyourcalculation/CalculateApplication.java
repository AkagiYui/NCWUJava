package com.akagiyui.testyourcalculation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.io.IOException;
import java.util.Objects;

public class CalculateApplication extends Application {
    @Override
    public void start(@NotNull Stage stage) throws IOException {
        // 添加主场景
        var fxmlURL = CalculateApplication.class.getResource("hello-view.fxml");
        var fxmlLoader = new FXMLLoader(fxmlURL); // 加载FXML文件
        var scene = new Scene(fxmlLoader.load(), 320, 240); // 创建场景
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet()); // 加载BootstrapFX样式
        stage.setScene(scene); // 设置场景

        // 设置图标
        var iconStream = CalculateApplication.class.getResourceAsStream("calculator.png");
        var icon = new Image(Objects.requireNonNull(iconStream));
        stage.getIcons().add(icon);

        stage.setTitle("Test Your Calculation"); // 设置标题
        stage.setResizable(false); // 禁止调整窗口大小
        stage.centerOnScreen(); // 窗口居中
        stage.show(); // 显示窗口
    }

    public static void main(String[] args) {
        launch();
    }
}