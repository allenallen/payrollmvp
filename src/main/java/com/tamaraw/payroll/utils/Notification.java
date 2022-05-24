package com.tamaraw.payroll.utils;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;

import java.util.Optional;

public class Notification {

    public static Optional<ButtonType> callAlert(Alert.AlertType alertType, String dialog) {
        Alert alert = new Alert(alertType);
        alert.setContentText(dialog);
        return alert.showAndWait();
    }

    public static void toast(String message, Control control) {
        Stage stage = (Stage) control.getScene().getWindow();
        final Popup popup = createPopup(message);
        popup.setOnShown(e -> {
            popup.setX(stage.getX() + stage.getWidth() / 2 - popup.getWidth() / 2);
            popup.setY(stage.getY() + stage.getHeight() / 1.2 - popup.getHeight() / 2);
        });
        popup.show(stage);

        new Timeline(new KeyFrame(
                Duration.millis(3000),
                ae -> popup.hide())).play();
    }

    public static void toast(String message, Window window) {
        Stage stage = (Stage) window;
        final Popup popup = createPopup(message);
        popup.setOnShown(e -> {
            popup.setX(stage.getX() + stage.getWidth() / 2 - popup.getWidth() / 2);
            popup.setY(stage.getY() + stage.getHeight() / 1.2 - popup.getHeight() / 2);
        });
        popup.show(stage);

        new Timeline(new KeyFrame(
                Duration.millis(3000),
                ae -> popup.hide())).play();
    }

    private static Popup createPopup(final String message) {
        final Popup popup = new Popup();
        popup.setAutoFix(true);
        Label label = new Label(message);
        label.getStylesheets().add(Notification.class.getResource("toast.css").toExternalForm());
        label.getStyleClass().add("popup");
        popup.getContent().add(label);
        return popup;
    }


}
