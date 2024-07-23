module com.example.sistema_votacion_escolar {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.sistema_votacion_escolar to javafx.fxml;
    exports com.example.sistema_votacion_escolar;
    opens com.example.sistema_votacion_escolar.Controllers to javafx.fxml;
    exports com.example.sistema_votacion_escolar.Controllers to javafx.fxml;
    opens com.example.sistema_votacion_escolar.Models to javafx.base;

}