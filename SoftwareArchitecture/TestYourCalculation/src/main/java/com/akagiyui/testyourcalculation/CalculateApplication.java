package com.akagiyui.testyourcalculation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.io.IOException;
import java.util.Objects;

public class CalculateApplication extends Application {
    @Override
    public void start(@NotNull Stage stage) throws IOException {
        // 导入主界面
        var borderPaneURL = CalculateApplication.class.getResource("hello-view.fxml");
        var borderPane = (BorderPane)new FXMLLoader(borderPaneURL).load(); // 加载FXML文件

        // 导入菜单栏
        var menuBarURL = CalculateApplication.class.getResource("menubar.fxml");
        var menuBar = (MenuBar)new FXMLLoader(menuBarURL).load();
        menuBar.prefWidthProperty().bind(stage.widthProperty()); // 菜单栏宽度与窗口宽度绑定
        borderPane.setTop(menuBar); // 将菜单栏放置在主界面的顶部

        // 创建主场景
        var scene = new Scene(borderPane, 320, 240); // 创建场景
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet()); // 加载BootstrapFX样式

        // 设置图标
        var iconStream = CalculateApplication.class.getResourceAsStream("calculator.png");
        var icon = new Image(Objects.requireNonNull(iconStream));
        stage.getIcons().add(icon);

        stage.setScene(scene); // 设置场景
        stage.setTitle("Test Your Calculation"); // 设置标题
        stage.setResizable(false); // 禁止调整窗口大小
        stage.centerOnScreen(); // 窗口居中

        // 加载配置
        var config = Config.getInstance();
        if (!config.loadFromFile()) {
            var alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("错误");
            alert.setHeaderText("配置文件加载失败");
            alert.setContentText("请检查配置文件config.yaml\n是否存在或格式是否正确");
            alert.showAndWait();
            System.exit(1);
        }

        stage.show(); // 显示窗口
    }

    public static void main(String[] args) {
        launch();
    }
}