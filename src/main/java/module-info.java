module com.example.gestioncartes {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.gestioncartes to javafx.fxml;
    exports com.example.gestioncartes;
}