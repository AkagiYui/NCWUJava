module com.akagiyui.testyourcalculation {
    requires javafx.controls;
    requires javafx.fxml;

    requires static lombok;
    requires static AnimateFX;
    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires org.jetbrains.annotations;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.dataformat.yaml;
    requires org.kordamp.ikonli.core;
    requires org.kordamp.ikonli.javafx;

    opens com.akagiyui.testyourcalculation to javafx.fxml, com.fasterxml.jackson.databind;
    exports com.akagiyui.testyourcalculation;
    exports com.akagiyui.testyourcalculation.equation;
    opens com.akagiyui.testyourcalculation.equation to com.fasterxml.jackson.databind, javafx.fxml;
    exports com.akagiyui.testyourcalculation.checker;
    opens com.akagiyui.testyourcalculation.checker to com.fasterxml.jackson.databind, javafx.fxml;
}
