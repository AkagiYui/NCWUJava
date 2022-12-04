package com.akagiyui.testyourcalculation.scene;

import com.akagiyui.testyourcalculation.CalculateApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
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

        // 导入菜单栏
        var menuBarURL = CalculateApplication.class.getResource("view/menubar.fxml");
        var menuBar = (MenuBar)new FXMLLoader(menuBarURL).load();
//        menuBar.prefWidthProperty().bind(stage.widthProperty()); // 菜单栏宽度与窗口宽度绑定

        var root = (BorderPane)getRoot();
        root.setTop(menuBar);

        getStylesheets().add(BootstrapFX.bootstrapFXStylesheet()); // 加载BootstrapFX样式
    }
}

