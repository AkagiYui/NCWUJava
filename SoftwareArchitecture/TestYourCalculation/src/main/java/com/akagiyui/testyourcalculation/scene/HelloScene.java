package com.akagiyui.testyourcalculation.scene;

import com.akagiyui.testyourcalculation.CalculateApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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
    }
}

