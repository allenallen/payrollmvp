module com.tamaraw.payroll {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    requires unirest.java;
    requires com.google.gson;

    opens com.tamaraw.payroll.models to com.google.gson;
    opens com.tamaraw.payroll.controllers to javafx.fxml;

    exports com.tamaraw.payroll;
}