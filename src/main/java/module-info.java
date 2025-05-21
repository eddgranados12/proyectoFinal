module org.example.torneos {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens org.example.torneos to javafx.fxml;
    exports org.example.torneos;
    exports org.example.torneos.controller;
    opens org.example.torneos.controller to javafx.fxml;
}