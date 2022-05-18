package com.tamaraw.payroll.controllers;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.tamaraw.payroll.HelloApplication;
import com.tamaraw.payroll.daos.UserDAO;
import com.tamaraw.payroll.models.User;
import com.tamaraw.payroll.services.UserService;
import com.tamaraw.payroll.utils.APIDefinitions;
import com.tamaraw.payroll.utils.SceneLoader;
import com.tamaraw.payroll.utils.Scenes;
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
    protected void onLoginBtnClicked() throws IOException, UnirestException {
        String username = this.username.getText();
        String password = this.password.getText();

        UserService userService = new UserService(APIDefinitions.USER_API);
        User user = userService.getUser(username);
        if (username.equals(user.getUserName()) && password.equals(user.getPassword())) {
            SceneLoader.loadScene((Stage) this.username.getScene().getWindow(), Scenes.MAIN);
        } else {
            label.setText("Wrong password!");
            label.setVisible(true);
        }
    }
}
