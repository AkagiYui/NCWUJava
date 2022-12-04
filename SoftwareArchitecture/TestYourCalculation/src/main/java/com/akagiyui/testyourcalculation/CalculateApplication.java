package com.akagiyui.testyourcalculation;

import com.akagiyui.testyourcalculation.scene.HelloScene;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Objects;

public class CalculateApplication extends Application {
    @Override
    public void start(@NotNull Stage stage) throws IOException {
        // 设置图标
        var iconStream = CalculateApplication.class.getResourceAsStream("image/calculator.png");
        var icon = new Image(Objects.requireNonNull(iconStream));
        stage.getIcons().add(icon);

        stage.setScene(new HelloScene()); // 设置场景
        stage.setTitle("Test Your Calculation"); // 设置标题
        stage.setResizable(false); // 禁止调整窗口大小
        stage.centerOnScreen(); // 窗口居中

        // 加载配置 TODO 加载失败应使用默认配置而非退出
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