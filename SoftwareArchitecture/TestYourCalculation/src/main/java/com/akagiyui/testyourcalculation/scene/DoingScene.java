package com.akagiyui.testyourcalculation.scene;

import com.akagiyui.testyourcalculation.CalculateApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.io.IOException;

public class DoingScene extends Scene {
    public DoingScene() throws IOException {
        super(
                new FXMLLoader(
                        CalculateApplication.class.getResource("view/doing-view.fxml")
                ).load(),
                320,
                240
        );
        getStylesheets().add(BootstrapFX.bootstrapFXStylesheet()); // 加载BootstrapFX样式

        var root = (BorderPane) getRoot();
        var menuBar = (MenuBar) root.lookup("#menuBar");
        menuBar.getMenus().forEach(menu -> menu.getItems().forEach(item -> item.setOnAction(event -> {
            var id = item.getId();
            switch (id) {
                case "closeExerciseMenuItem" -> {
                    var stage = (Stage) getWindow();
                    try {
                        stage.setScene(new HelloScene());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                case "exitMenuItem" -> {
                    var stage = (Stage) getWindow();
                    var request = new WindowEvent(getWindow(), WindowEvent.WINDOW_CLOSE_REQUEST);
                    stage.getOnCloseRequest().handle(request);
                    if (!request.isConsumed()) {
                        stage.close();
                    }
                }
                case "about" -> {
                    var alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("关于");
                    alert.setHeaderText("TestYourCalculation v1.0.0");
                    alert.setContentText("作者: AkagiYui\nQQ: 1050314133");
                    alert.showAndWait();
                }
                default -> System.out.println("未知菜单项：" + id);
            }
        })));
    }
}

