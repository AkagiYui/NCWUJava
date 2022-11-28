module com.akagiyui.testyourcalculation {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.akagiyui.testyourcalculation to javafx.fxml;
    exports com.akagiyui.testyourcalculation;
}