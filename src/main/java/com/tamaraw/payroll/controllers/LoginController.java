package com.tamaraw.payroll.controllers;

import com.tamaraw.payroll.HelloApplication;
import com.tamaraw.payroll.daos.UserDAO;
import com.tamaraw.payroll.models.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Label label;

    @FXML
    protected void onLoginBtnClicked() throws IOException {
        String username = this.username.getText();
        String password = this.password.getText();
        User user = UserDAO.getSuperUser();
        if (username.equals(user.getUserName()) && password.equals(user.getPassword())) {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("controllers/main.fxml"));
            Stage mainStage = (Stage) this.username.getScene().getWindow();
            mainStage.setTitle("Home");
            mainStage.setScene(new Scene(fxmlLoader.load()));
        } else {
            label.setText("Wrong password!");
            label.setVisible(true);
        }
    }
}
