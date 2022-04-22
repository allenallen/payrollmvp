module com.tamaraw.payroll {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.tamaraw.payroll.controllers to javafx.fxml;
    exports com.tamaraw.payroll;
}