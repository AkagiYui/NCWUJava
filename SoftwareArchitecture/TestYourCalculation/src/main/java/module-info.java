module com.akagiyui.testyourcalculation {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires org.jetbrains.annotations;
    requires static lombok;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.dataformat.yaml;

    opens com.akagiyui.testyourcalculation to javafx.fxml, com.fasterxml.jackson.databind;
    exports com.akagiyui.testyourcalculation;
    exports com.akagiyui.testyourcalculation.controller;
    opens com.akagiyui.testyourcalculation.controller to com.fasterxml.jackson.databind, javafx.fxml;
}