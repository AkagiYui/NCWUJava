package com.akagiyui.testyourcalculation.scene;

import animatefx.animation.BounceInDown;
import animatefx.animation.BounceOutUp;
import com.akagiyui.testyourcalculation.CalculateApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.io.File;
import java.io.IOException;

public class DoingScene extends Scene {
    BorderPane root;
    StackPane centerPane;
    HBox buttonGroup;
    private ExerciseLayout exerciseLayout;

    public DoingScene() throws IOException {
        super(
                new FXMLLoader(
                        CalculateApplication.class.getResource("view/doing-view.fxml")
                ).load(),
                320,
                240
        );
        getStylesheets().add(BootstrapFX.bootstrapFXStylesheet()); // 加载BootstrapFX样式

        getRoot().sceneProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println(observable);
            System.out.println(oldValue);
            System.out.println(newValue);
            if (newValue != null) {
                ((Stage)newValue.getWindow()).setTitle("Test Your Calculation");
            }
        });

        // 获取组件
        root = (BorderPane)getRoot();
        centerPane = (StackPane)root.getCenter();
        var menuBar = (MenuBar)root.lookup("#menuBar");
        var createExerciseButton = (Button)root.lookup("#createExerciseButton");
        var refreshButton = (Button)root.lookup("#refreshButton");
        var checkButton = (Button)root.lookup("#checkButton");
        buttonGroup = (HBox)root.lookup("#buttonGroup");
        var successAlert = (TextFlow)root.lookup("#successAlert");

        // 初始化
        exerciseLayout = new ExerciseLayout();


        // 设置菜单事件
        menuBar.getMenus().forEach(menu -> menu.getItems().forEach(item -> item.setOnAction(event -> {
            var id = item.getId();
            switch (id) {
                case "saveExerciseMenuItem" -> {
                    if (exerciseLayout == null || exerciseLayout.count() == 0) {
                        var alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("警告");
                        alert.setHeaderText("没有题目");
                        alert.setContentText("请先生成题目");
                        alert.showAndWait();
                        return;
                    }
                    var fileChooser = new FileChooser();
                    fileChooser.setTitle("保存练习簿");
                    fileChooser.getExtensionFilters().add(
                            new FileChooser.ExtensionFilter("练习簿", "*.json")
                    );

                    var stage = getWindow();
                    var file = fileChooser.showSaveDialog(stage);
                    if (file == null) {
                        return;
                    }
                    exerciseLayout.saveExercise(file);
                }
                case "closeExerciseMenuItem" -> {
                    if (exerciseLayout != null && exerciseLayout.count() != 0 && !exerciseLayout.isSaved()) {
                        var alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("确认");
                        alert.setHeaderText("练习簿尚未保存");
                        alert.setContentText("确定关闭习题簿吗？");
                        var result = alert.showAndWait();
                        if (result.isPresent() && result.get().getButtonData().isCancelButton()) {
                            return;
                        }
                    }
                    var stage = (Stage) getWindow();
                    try {
                        stage.setScene(new HelloScene());
                        stage.sizeToScene();
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
                    alert.setHeaderText("Test Your Calculation v1.0.0");
                    alert.setContentText("作者: AkagiYui\nQQ: 1050314133");
                    alert.showAndWait();
                }
                default -> System.out.println("未知菜单项：" + id);
            }
        })));

        createExerciseButton.setOnMouseClicked(event -> {
            exerciseLayout.refresh();
            showExerciseLayout();
        });

        refreshButton.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                exerciseLayout.refresh();
            }
        });

        checkButton.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                if (exerciseLayout.check()){
                    successAlert.setVisible(true);
                    var inAnimate = new BounceInDown(successAlert);
                    var outAnimate = new BounceOutUp(successAlert);
                    outAnimate.setDelay(new Duration(3000));
                    outAnimate.setOnFinished(e -> successAlert.setVisible(false));
                    inAnimate.playOnFinished(outAnimate);
                    inAnimate.play();
                }
            }
        });
    }

    public DoingScene(File file) throws IOException {
        this();
        exerciseLayout.loadExercise(file);
        showExerciseLayout();
    }

    private void showExerciseLayout() {
        var bp = (BorderPane)centerPane.getChildren().get(0);
        bp.setCenter(exerciseLayout);
        buttonGroup.setVisible(true);
    }
}
