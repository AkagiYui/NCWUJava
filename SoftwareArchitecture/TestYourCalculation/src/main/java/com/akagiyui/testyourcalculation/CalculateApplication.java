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
//        stage.setResizable(false); // 禁止调整窗口大小
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
//            alert.initOwner(stage);

            var result = alert.showAndWait();
            if (result.isPresent() && result.get().getButtonData().isDefaultButton()) {
                return;
            }
            event.consume();
        });

//        // 加载配置 TODO 加载失败应使用默认配置而非退出
//        var config = Config.getInstance();
//        if (!config.loadFromFile()) {
//            var alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("错误");
//            alert.setHeaderText("配置文件加载失败");
//            alert.setContentText("请检查配置文件config.yaml\n是否存在或格式是否正确");
//            alert.showAndWait();
//            System.exit(1);
//        }

        var helloScene = new HelloScene();

        stage.setScene(helloScene); // 设置场景
        stage.show(); // 显示窗口
    }

    public static void main(String[] args) {
        launch();
    }
}
