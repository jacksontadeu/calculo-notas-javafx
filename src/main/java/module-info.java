module com.jtmjinfo.calculonotas {
    requires javafx.fxml;
    requires java.sql;
    requires com.github.librepdf.openpdf;
    requires org.controlsfx.controls;
    requires java.desktop;


    opens com.jtmjinfo.calculonotas to javafx.fxml;
    opens com.jtmjinfo.calculonotas.controller to javafx.fxml;
    opens com.jtmjinfo.calculonotas.views to javafx.fxml;
    exports com.jtmjinfo.calculonotas;
    exports com.jtmjinfo.calculonotas.util;
    opens com.jtmjinfo.calculonotas.util to javafx.fxml;
    exports com.jtmjinfo.calculonotas.dao;
    opens com.jtmjinfo.calculonotas.dao to javafx.fxml;
    opens com.jtmjinfo.calculonotas.model to javafx.base;
}