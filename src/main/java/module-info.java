module com.jtmjinfo.calculonotas {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.jtmjinfo.calculonotas to javafx.fxml;
    opens com.jtmjinfo.calculonotas.controller to javafx.fxml;
    opens com.jtmjinfo.calculonotas.views to javafx.fxml;
    exports com.jtmjinfo.calculonotas;
}