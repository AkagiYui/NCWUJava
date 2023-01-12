package com.akagiyui.testyourcalculation;

import com.akagiyui.testyourcalculation.scene.HelloScene;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Modality;
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

        stage.setTitle("Test Your Calculation"); // 设置标题
        stage.setResizable(true); // 允许调整窗口大小
        stage.centerOnScreen(); // 窗口居中

        // 请求退出事件
        stage.setOnCloseRequest(event -> {
            if (stage.getScene() instanceof HelloScene) {
                return;
            }
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("退出");
            alert.setHeaderText("确认退出吗？");
            alert.initModality(Modality.APPLICATION_MODAL); // 应用程序级别的模态窗口
            var result = alert.showAndWait();
            if (result.isPresent() && result.get().getButtonData().isDefaultButton()) {
                return;
            }
            event.consume(); // 消费事件
        });

        stage.setScene(new HelloScene()); // 设置场景
        stage.show(); // 显示窗口
    }

    public static void main(String[] args) {
        launch();
    }
}
