package com.tamaraw.payroll.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class Notification {

    public static Optional<ButtonType> callAlert(Alert.AlertType alertType, String dialog) {
        Alert alert = new Alert(alertType);
        alert.setContentText(dialog);
        return alert.showAndWait();
    }

}
